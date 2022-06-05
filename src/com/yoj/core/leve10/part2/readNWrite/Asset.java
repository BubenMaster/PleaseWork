package com.yoj.core.leve10.part2.readNWrite;

import java.io.*;

public class Asset {
    public Asset(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Asset() {
        this.name = null;
        this.price = 0;
    }


    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Asset asset = (Asset) o;

        if (Double.compare(asset.price, price) != 0) return false;
        return name != null ? name.equals(asset.name) : asset.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public void save(OutputStream output) {
        Writer writer = new BufferedWriter( new OutputStreamWriter(output));
        try {
            writer.write(name + "\n");
            writer.write(String.valueOf(price) + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(BufferedReader reader) {
        try {
            name = reader.readLine();
            price = Double.parseDouble(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}