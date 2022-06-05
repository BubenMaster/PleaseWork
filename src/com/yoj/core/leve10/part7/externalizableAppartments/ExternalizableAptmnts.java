package com.yoj.core.leve10.part7.externalizableAppartments;


import com.yoj.core.leve10.part7.FamilyRnW.Solution;

import java.io.*;
import java.util.List;

/*
Externalizable для апартаментов
*/

public class ExternalizableAptmnts {

        public static class Apartment implements Externalizable {

            private String address;
            private int year;

            /**
             * Mandatory public no-arg constructor.
             */
            public Apartment() {
                super();
            }

            public Apartment(String addr, int y) {
                address = addr;
                year = y;
            }

            /**
             * Prints out the fields used for testing!
             */
            public String toString() {
                return ("Address: " + address + "\n" + "Year: " + year);
            }

            @Override
            public void writeExternal(ObjectOutput out) throws IOException {
                out.writeObject(address);
                out.writeInt(year);
            }

            @Override
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
                this.address = (String) in.readObject();
                this.year = in.readInt();
            }
        }

        public static void main(String[] args) {
            Apartment apptmnt = new Apartment("room", 143);
            Apartment apptmntClone = null;

            File tmpFile = null;
            try {
                tmpFile = File.createTempFile("tmp", null, new File("G:\\JavaWorkspace\\Inellij Workspace\\Please Work\\src\\com\\yoj\\core\\leve10\\part7\\externalizableAppartments"));
                ObjectOutputStream ooutput = new ObjectOutputStream(new FileOutputStream(tmpFile));
                ObjectInputStream oinput = new ObjectInputStream(new FileInputStream(tmpFile));
                ooutput.writeObject(apptmnt);
                Object object = oinput.readObject();
                apptmntClone = (Apartment) object;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println(apptmntClone.address + " " + apptmntClone.year);

        }
    }



