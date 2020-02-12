package com.nt.util;

import com.nt.domain.Customer;
import com.nt.domain.Sale;
import com.nt.domain.Salesman;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {

    private static final String folderIn = System.getProperty("user.home") + "\\data\\in\\";
    private static final String folderOut = System.getProperty("user.home") + "\\data\\out\\";

    @Autowired
    private static FileWriter file;

    public static void createDirectory(String folder) {
        File file = new File(folder);
        if (!file.exists())
            file.mkdir();
    }

    public static String getFolder(boolean isFolderIn) {
        if (isFolderIn)
            return folderIn;
        else
            return folderOut;
    }

    public static boolean createOutputFile(List<String> listFormated, Customer customer, Sale sale, Salesman salesman) {

        customer.getCustomersFromList(listFormated);
        sale.getSalesFromList(listFormated);
        salesman.getSalesmansFromList(listFormated);

        FileUtil.createDirectory(FileUtil.getFolder(false));
        try {
            file = new FileWriter(new File(FileUtil.getFolder(false) + "OutputFile.done.txt"));
            file.write("Amount of clients in the input file: " + customer.amountOfClients() + "\r\n"
                    + "Amount of salesman in the input file: " + salesman.amountOfSalesman() + "\r\n"
                    + "Id of the most expensive sale: " + sale.getIdOfTheMostExpensiveSale() + "\r\n"
                    + "Worst salesman ever: " + sale.getWorstSalesmanEver());
            file.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String replaceAscIIDelimiter(String code) {
        return code.replaceAll("[^\\p{ASCII}]", ";");
    }
}
