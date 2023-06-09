import java.util.*;

public class Main {
    private static String continue_key = "Y", continue_key2 = "Y";
    private static final Scanner in = new Scanner(System.in);//Scanner

    public static void main(String[] args) {

        String[] idList = new String[0];   //student id
        String[] nameList = new String[0]; //student name
        int[] prfMarksList = new int[0];    //prf marks
        int[] dbmsMarksList = new int[0];      //dbms marks

        while (!continue_key2.equals("N")) {
            String input = topic();
            switch (input) {
                case "1" -> {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("|                                   ADD NEW STUDENT                                        |");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    do {
                        int size = idList.length;
                        idList = addNewStudent(idList);
                        if (size < idList.length) {

                            nameList = addNewStdName(nameList);
                            prfMarksList = increase(prfMarksList);
                            dbmsMarksList = increase(dbmsMarksList);
                            System.out.print("Student has been added successfully. Do you want to Add another Student(Y/N): ");
                            continue_key = in.next();
                        }
                    } while (!continue_key.equals("N") && !continue_key.equals("n"));
                }
                case "2" -> {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("|                              Add New Student With Marks                                  |");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    do {
                        int size = idList.length;
                        idList = addNewStudent(idList);
                        if (size < idList.length) {
                            nameList = addNewStdName(nameList);
                            prfMarksList = input2Marks(prfMarksList, 1);    //
                            dbmsMarksList = input2Marks(dbmsMarksList, 2);//
                            System.out.print("\nStudent has been added successfully. Do you want to add a new student (Y/N) : ");
                            continue_key = in.next();
                        }
                    } while (!continue_key.equals("N") && !continue_key.equals("n"));
                }
                case "3" -> {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("|                                      Add Marks                                           |");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    do {
                        System.out.print("\nEnter Student ID : ");
                        String std_id = in.next();
                        int key = checkStd(std_id, idList);
                        if (key != -1) {
                            System.out.println("Student name     : " + nameList[key]);
                            System.out.println();
                            if (checkArray(prfMarksList, key) && checkArray(dbmsMarksList, key)) {
                                prfMarksList[key] = inputMarks43(prfMarksList, key, 1);        //
                                dbmsMarksList[key] = inputMarks43(dbmsMarksList, key, 2);    //
                                System.out.print(" \nStudent Added.Do you want to Add another Student(Y/N): ");
                            } else {
                                System.out.println("This student's marks have been already added.\nIf you want to update the marks, please [4] Update Marks option.");
                                System.out.print("Do you want to Add another Student(Y/N) : ");
                            }
                        } else {
                            System.out.print("Invalid Student ID. Do you want to search again? (Y/N) : ");
                        }
                        continue_key = in.next();
                    } while (!continue_key.equals("N") && !continue_key.equals("n"));
                }
                case "4" -> {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("|                              Update Student Details                                      |");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println(Arrays.toString(nameList));
                    do {
                        System.out.print("\nEnter Student ID : ");
                        String std_id = in.next();
                        int key = checkStd(std_id, idList);
                        if (key != -1) {
                            System.out.println("Student name     : " + nameList[key]);
                            nameList[key] = changeName();
                            System.out.print("Do you want to update another Student(Y/N) : ");
                        } else {
                            System.out.print("\nInvalid Student ID. Do you want to search again? (Y/N) : ");
                        }
                        continue_key = in.next();
                    } while (!continue_key.equals("N") && !continue_key.equals("n"));
                }
                case "5" -> {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("|                              Update Student Details                                      |");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    do {
                        System.out.print("\nEnter Student ID : ");
                        String std_id = in.next();
                        int key = checkStd(std_id, idList);
                        System.out.println("Name : " + nameList[key]);
                        if ((prfMarksList[key] > -1) && (dbmsMarksList[key] > -1)) {
                            System.out.println("Programming Fundamentals marks   : " + prfMarksList[key] + " \nDatabase Managment Systems marks : " + dbmsMarksList[key]);
                            System.out.print("Enter new Programming Fundamentals marks   : ");
                            prfMarksList[key] = in.nextInt();
                            System.out.print("Enter new Database Managment Systems marks : ");
                            dbmsMarksList[key] = in.nextInt();
                            System.out.print("\nMarks have been updated successfully\nDo you want to update marks for another student?(Y/N)  : ");
                        } else {
                            System.out.print("\nThis student's marks yet to be added.\nDo you want to update marks for another student?(Y/N) :  ");
                        }
                        continue_key = in.next();
                    } while (!continue_key.equals("N") && !continue_key.equals("n"));
                }
                case "6" -> {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("|                                   Delete Student                                         |");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    do {
                        System.out.print("\nEnter Student ID : ");
                        String std_id = in.next();
                        int key = checkStd(std_id, idList);
                        if (key != -1) {
                            idList = deleteStudent(idList, key);
                            nameList = deleteStudent(nameList, key);
                            prfMarksList = deleteStudent(key, prfMarksList);
                            dbmsMarksList = deleteStudent(key, dbmsMarksList);
                            System.out.print("Student has been deleted sucessfully.\n\nDo you want to delete another student? (Y/N) : ");
                        } else {
                            System.out.print("Invalid Student ID. Do you want to search again? (Y/N) : ");
                        }
                        continue_key = in.next();
                    } while (!continue_key.equals("N") && !continue_key.equals("n"));
                }
                case "7" -> {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("|                                Print Students Details                                    |");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    do {
                        System.out.print("\nEnter Student ID : ");
                        String std_id = in.next();
                        int key = checkStd(std_id, idList);
                        if (key >= 0) {
                            System.out.println("Name             : " + nameList[key]);
                            if ((prfMarksList[key] > -1) && (dbmsMarksList[key] > -1)) {
                                printStudentDetails(nameList, prfMarksList, dbmsMarksList, key);
                                System.out.print("\n\nDo you want to print marks for another student? (Y/N) : ");
                            } else {
                                System.out.print("\nThis student's marks yet to be added.\nDo you want to print marks for another student?(Y/N) :  ");
                            }
                        } else {
                            System.out.print("Invalid Student ID. Do you want to search again? (Y/N) : ");
                        }
                        continue_key = in.next();
                    } while (!continue_key.equals("N") && !continue_key.equals("n"));
                }
                case "8" -> {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("|                                  Print Students Rank                                     |");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    int[] total = findTotal(prfMarksList, dbmsMarksList);
                    do {
                        int key = 1;
                        printStdRanks(total, nameList, idList, key);
                        System.out.print("Press Y to go Main menu : ");
                        continue_key = in.next();
                    } while (!continue_key.equals("Y") && !continue_key.equals("y"));
                }
                case "9" -> {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("|                           Best in Programming Fundamentals                                |");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    do {
                        int key = 1;
                        printRanks(prfMarksList, dbmsMarksList, nameList, idList, key);
                        System.out.print("Press Y to go Main menu : ");
                        continue_key = in.next();
                    } while (!continue_key.equals("Y") && !continue_key.equals("y"));
                }
                case "10" -> {
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("|                         Best in Database Manegment Systems                               |");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    do {
                        int key = 2;
                        printRanks(prfMarksList, dbmsMarksList, nameList, idList, key);
                        System.out.print("Press Y to go Main menu : ");
                        continue_key = in.next();
                    } while (!continue_key.equals("Y") && !continue_key.equals("y"));
                }
                default -> {
                    System.out.println("Error: Invalid input.");
                    System.out.print("Press Y to go Main menu or N to End the Program : ");
                    continue_key2 = in.next();
                }
            }
        }
        clearConsole();
        System.out.print("\nThank You!...");
    }

    public static String topic() {                                        //topics
        clearConsole();
        System.out.println("--------------------------------------------------------------------------------------------\n|\t\t\t\tWELCOME TO GDSE MARKS MANAGEMENT SYSTEM  \t \t \t   |");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.print("\n [1]. Add New Student  \t\t\t  [2]. Add New Student with marks\n [3]. Add Marks \t\t\t  [4]. Update Student Details\n [5]. Update Marks ");
        System.out.println(" \t\t\t  [6]. Delete Student\n [7]. Print Student Details \t\t  [8]. Print Students Ranks\n [9]. Best in Programming Fundamentals \t [10]. Best in Database Manegment Systems\n");
        System.out.print("Enter an option to continue  : ");
        String input = in.next();
        clearConsole();
        continue_key = "Y";
        return input;
    }

    public static String[] addNewStudent(String[] sId) {                //Add ID
        System.out.print("\nEnter Student ID  : ");
        String id = in.next();
        if (isExists(id, sId)) {
            return sId = increase(sId, id);
        }
        System.out.println("The Student ID already Exists");
        return sId;
    }

    public static String[] addNewStdName(String[] sName) {                //Add name
        System.out.print("Enter Student Name: ");
        String name = in.next();
        System.out.println();

        sName = increase(sName, name);

        return sName;
    }

    public static boolean isExists(String key, String[] temp) {        //checking whether the id is duplicated
        for (String s : temp) {
            if (s.equals(key)) {
                return false;
            }
        }
        return true;
    }

    public static String[] increase(String[] Temp, String key) {
        //increase string array size
        String[] temp = new String[Temp.length + 1];
        System.arraycopy(Temp, 0, temp, 0, Temp.length);
        temp[Temp.length] = key;
        return temp;
    }

    public static int[] increase(int[] Temp) {                            //increase integer array size
        int[] temp = new int[Temp.length + 1];
        System.arraycopy(Temp, 0, temp, 0, Temp.length);
        temp[temp.length - 1] = -1;
        return temp;
    }

    public static int[] input2Marks(int[] marksArray, int type) {    //add marks method
        int[] temp = marksArray;
        String subject = (type == 1) ? "Programming  Fundamentals " : "Database Managment Systems";
        boolean flag = true;
        l1:
        do {
            System.out.print("Enter " + subject + " Marks : ");
            int mark = in.nextInt();
            if (mark < 0 || mark > 100) {
                System.out.println("Invalid marks. Please enter correct Marks");
                continue l1;
            } else {
                marksArray = increase(marksArray);
                marksArray[marksArray.length - 1] = mark;
                temp = marksArray;
                flag = false;
            }
        } while (flag);
        return temp;
    }

    public static int checkStd(String key, String[] stdId) {            //checking whether the student is exists

        for (int i = 0; i < stdId.length; i++) {
            if (stdId[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean checkArray(int[] marks, int key) {            //check whether the mark is exists
        for (int i = 0; i < marks.length; i++) {
            if (marks[key] == -1) {
                return true;
            }
        }
        return false;
    }

    public static int inputMarks43(int[] marksArray, int key, int type) {    //input prf and dbms marks for option 3
        int marks = 0;
        String subject = (type == 1) ? "Programming  Fundamentals " : "Database Managment Systems";
        boolean flag = true;
        l1:
        do {
            System.out.print("Input " + subject + " Marks : ");
            marks = in.nextInt();
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks, Please enter correct Marks.\n");
                continue l1;
            } else {
                marksArray[key] = marks;
                flag = false;
            }
        } while (flag);
        return marksArray[key];
    }

    public static String changeName() {                                    //Update students name
        System.out.print("\nEnter the new student name   : ");
        String newName = in.next();
        System.out.println("\nStudent details has been updated successfully.");
        return newName;
    }

    public static String[] deleteStudent(String[] array, int key) {    //for string type
        String[] temp = new String[array.length - 1];
        int j = 0;
        for (int i = 0; i < temp.length; i++) {
            if (key == j) {
                j++;
            }
            temp[i] = array[j++];
        }
        return temp;
    }

    public static int[] deleteStudent(int key, int[] array) {            //for int type (overloaded)
        int[] temp = new int[array.length - 1];
        int j = 0;
        for (int i = 0; i < temp.length; i++) {
            if (key == j) {
                j++;
            }
            temp[i] = array[j++];
        }
        return temp;
    }

    public static void printStudentDetails(String[] sName, int[] prfMarks, int[] dbmsMarks, int key) {    //print student details
        int total = prfMarks[key] + dbmsMarks[key];
       // double avg = (double) total / 2.0;
        int[] totalArray = findTotal(prfMarks, dbmsMarks);
        int rank = findRank(totalArray, key);
        System.out.println("+----------------------------------+--------------+");
        System.out.printf("|Programming Fundamentals marks    |%14d|\n|Database Managment Systems marks  |%14d|\n", prfMarks[key], dbmsMarks[key]);
        System.out.printf("|Total  Marks                      |%14d|\n|Avg. Marks                        |%14.1f|\n", total, (double) total / 2.0);
        System.out.print("|Rank                              |\t" + rank);
        System.out.print((rank == 1) ? "  (FIRST)|\n" : (rank == 2) ? " (SECOND)|\n" : (rank == 3) ? "  (THIRD)|\n" : "        |\n");
        System.out.println("+----------------------------------+--------------+");
    }

    public static int[] findTotal(int[] prfMarks, int[] dbmsMarks) {    //fimd total
        int[] total = new int[prfMarks.length];
        for (int i = 0; i < prfMarks.length; i++) {
            if (prfMarks[i] > -1 && dbmsMarks[i] > -1) total[i] = prfMarks[i] + dbmsMarks[i];
            else total[i] = -1;
        }
        return total;
    }

    public static int findRank(int[] totalArray, int key) {                //find rank
        int[] tempArray = new int[totalArray.length]; //temp totals
        System.arraycopy(totalArray, 0, tempArray, 0, totalArray.length);
        tempArray = sort(tempArray); //sorted totals
        int[] rank = new int[totalArray.length];
        for (int i = 0; i < totalArray.length; i++) {
            for (int j = 0; j < totalArray.length; j++) {
                if (totalArray[i] == tempArray[j]) {
                    rank[i] = tempArray.length - j;
                }
            }
        }
        return rank[key];
    }

    public static int[] sort(int[] array) {                            //sort int arrays
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - (i + 1); j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static void printStdRanks(int[] total, String[] sName, String[] sId, int key) {
        int[] tempTotal = new int[total.length];
        System.arraycopy(total, 0, tempTotal, 0, total.length);
        int[] rank = findAllRank(tempTotal);//unsorted ranks
        int[] sortRank = new int[rank.length];
        System.arraycopy(rank, 0, sortRank, 0, rank.length);
        sortRank = sort(sortRank);
        tempTotal = sort(tempTotal);
        int[] index = new int[tempTotal.length];
        l1:
        for (int i = 0; i < tempTotal.length; i++) {
            for (int j = 0; j < tempTotal.length; j++) {
                if (tempTotal[i] == total[j]) {
                    index[i] = j;
                    continue l1;
                }
            }
        }
        System.out.printf("+-------+-------+---------+------------+---------------+\n|Rank   |ID     |Name     |%10s |  %12s|\n+-------+-------+---------+------------+---------------+\n", "Total Marks", "Average Marks");
        for (int i = total.length - 1; 0 <= i; i--) {
            if (tempTotal[i] > -1) {
                System.out.printf("|%-7d|%-7s|%-9s|%-12d|%-15.2f|\n", rank[index[i]], sId[index[i]], sName[index[i]], total[index[i]], (double) total[index[i]] / 2.0);
            }
        }
        System.out.println("+-------+-------+---------+------------+---------------+\n");
    }

    public static int[] findAllRank(int[] totalArray) {                //find and return rank array
        int[] tempArray = new int[totalArray.length];  //temp totals
        System.arraycopy(totalArray, 0, tempArray, 0, tempArray.length);
        tempArray = sort(tempArray); //sorted totals
        int[] rank = new int[totalArray.length];
        for (int i = 0; i < totalArray.length; i++) {
            for (int j = 0; j < totalArray.length; j++) {
                if (totalArray[i] == tempArray[j]) {
                    rank[i] = tempArray.length - j;
                }
            }
        }
        return rank;
    }

    public static void printRanks(int[] prfMarks, int[] dbmsMarks, String[] sName, String[] sId, int key) {
        int[] marks = new int[prfMarks.length];
        marks = prfMarks;
        int[] marks2 = new int[prfMarks.length];
        marks2 = dbmsMarks;
        String sub1 = "PRF Marks", sub2 = "DBMS Marks";
        if (key == 2) {
            marks = dbmsMarks;
            marks2 = prfMarks;
            sub1 = "DBMS Marks";
            sub2 = "PRF  Marks";
        }
        int[] tempMarks = new int[marks.length];
        System.arraycopy(marks, 0, tempMarks, 0, marks.length);
        int[] Ranks = new int[marks.length];
        Ranks = findAllRank(marks);
        int[] sortRanks = new int[Ranks.length];
        System.arraycopy(Ranks, 0, sortRanks, 0, Ranks.length);
        sortRanks = sort(sortRanks);
        tempMarks = sort(tempMarks);
        int[] index = new int[marks.length];
        l1:
        for (int i = 0; i < tempMarks.length; i++) {
            for (int j = 0; j < tempMarks.length; j++) {
                if (tempMarks[i] == marks[j]) {
                    index[i] = j;
                    continue l1;
                }
            }
        }
        System.out.printf("+-------+-------+---------+----------+----------+\n|Rank   |ID     |Name     |%10s|%9s|\n+-------+-------+---------+----------+----------+\n", sub1, sub2);
        for (int i = marks.length - 1; 0 <= i; i--) {
            if (tempMarks[i] > -1)
                System.out.printf("|%-7d|%-7s|%-9s|%-10d|%-10d|\n", Ranks[index[i]], sId[index[i]], sName[index[i]], marks[index[i]], marks2[index[i]]);
        }
        System.out.println("+-------+-------+---------+----------+----------+");
    }

    public final static void clearConsole() { //clear console
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}


