# Agibank Watcher

Java solution for Agibank Watcher challenge. Read .DAT file and output .done file with validations

## Program solution

This program was created using Java with Spring boot as build tool and Junit for the testing. 
The program read .dat file model and split data by 'ç'. The output .done file gives the number of costumers, number of salesman, the worst salesman in the file and rhe id of most expansive sale

## Input file

The input file from where the application will read. Should have one information per line splited by 'ç'.
See the example:

```
001ç1234567891234çPedroç50000 
001ç3245678865434çPauloç40000.99 
002ç2345675434544345çJose da SilvaçRural 
002ç2345675433444345çEduardo PereiraçRural 
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro 
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo 
```
