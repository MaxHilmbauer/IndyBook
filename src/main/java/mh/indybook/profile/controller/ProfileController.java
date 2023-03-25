package mh.indybook.profile.controller;

import mh.indybook.profile.model.Profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProfileController {

    public Profile readProfile(File profileFile) throws FileNotFoundException {
        Scanner sc = new Scanner(profileFile);
        Profile profile = new Profile();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("Rooms:")) {
                while ((line = sc.nextLine()).contains(" - ")) {
                    String priorityRoom = line.split(" - ")[1];
                    //String room =
                    //profile.getRooms().add(room);
                }
            }
            if (line.equals("Teachers:")) {
                while ((line = sc.nextLine()).contains(" - ")) {
                    String teacher = line.split(" - ")[1];
                    //profile.getTeachers().add(teacher);
                    // test comment
                }
            }
            if (line.contains("Subject:")) {
                while ((line = sc.nextLine()).contains(" - ")) {
                    String subjectActivity = line.split(" - ")[1];
                    profile.getSubjectActivity().add(subjectActivity);
                }

            }
        }

        return profile;
    }

}
