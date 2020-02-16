package com.nt.util;

import com.nt.domain.Customer;
import com.nt.domain.Sale;
import com.nt.domain.Salesman;
import com.nt.service.CustomerService;
import com.nt.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {

    private static final String folderIn = System.getProperty("user.home") + "\\data\\in\\";
    private static final String folderOut = System.getProperty("user.home") + "\\data\\out\\";
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private static FileWriter file;

    public static void createDirectory(String folder) {
        File file = new File(folder);
        if (!file.exists())
            file.mkdir();
    }

    public static String getFolderIn() {
            return folderIn;
    }

    public static String getFolderOut() {
        return folderOut;
    }

    public static boolean createOutputFile(List<String> listFormated) {

        //customer.getCustomersFromList(listFormated);
        //Sale sale = new Sale();
        //sale.getSalesFromList(listFormated);

        CustomerService customerService = new CustomerService();

        Customer customer = new Customer();

        for (String line : listFormated) {

            switch (line.substring(0,3)) {
                case "001": break;
                case "002": customer.getCustomerList().add(customerService.getCustomerData(line)); break;
                case "003": break;
                default: break;
            }


        }
        //salesman.getSalesmansFromList(listFormated);

        FileUtil.createDirectory(folderOut);
        try {
            file = new FileWriter(new File(folderOut + "OutputFile.done.txt"));
            file.write("Amount of clients in the input file: " + customer.getCustomerList().size() + "\r\n"
                    //+ "Amount of salesman in the input file: " + salesman.amountOfSalesman() + "\r\n"
                    //+ "Id of the most expensive sale: " + sale.getIdOfTheMostExpensiveSale() + "\r\n"
                    //+ "Worst salesman ever: " + sale.getWorstSalesmanEver()
                    );
            file.close();
            return true;
        } catch (IOException e) {
            logger.error("Write file error: Exception:" + e.getMessage());
            return false;
        }
    }

    /*public static String replaceAscIIDelimiter(String code) {
        return code.replaceAll("[^\\p{ASCII}]", ";");
    }*/
}
