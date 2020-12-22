package com.poa.tempscanner.model;

import android.print.PrinterInfo;

public class CreateSampleData {

    public static PrintModel createData() {
        PrintModel printModel = new PrintModel();
        printModel.setMac("8CFCA004F0AC");
        printModel.setUserId("1");
        printModel.setCheckTime(1600705186106L);
        printModel.setTimeZone("Europe London");
        printModel.setName("");
        printModel.setType(3);
        printModel.setCardNo("");
        printModel.setIdCardNo("");
        printModel.setTemperature("36.2");
        printModel.setMask(1);
        printModel.setPassType(true);
        printModel.setTotalFaceNumber(1);
        printModel.setTotalPeopleNumber(1);
        printModel.setExtra("");
        printModel.setGroupId(0);
        printModel.setCheckPic("\\/9j\\/4AAQSkZJRgABAQAAAQABAAD\\/2wBDAFA3PEY8MlBGQUZaVVBfeMiCeG5uePWvuZHI\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/2wBDAVVaWnhpeOuCguv\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/wAARCAMIAtADASIAAhEBAxEB\\/8QAGQABAQEBAQEAAAAAAAAAAAAAAAECAwQF\\/8QALBABAQACAgEEAgICAwEBAAMAAAECEQMxIRIyQWFRcQQiM4ETI0KhUpGx4f\\/EABYBAQEBAAAAAAAAAAAAAAAAAAABAv\\/EABcRAQEBAQAAAAAAAAAAAAAAAAABEQL\\/2gAMAwEAAhEDEQA\\/AMKqKiMcvUdHLl7BjQqxFZdeHPX9axpm+PM7EewY4s\\/Vj9tqoAIACjOctWpag4XxU3uN5xy6oFRagICyASO\\/Fxb\\/ALZdfEZ4uP1Xd9seoDSoooAAAAAgAAgAAAAAAAAICggKgAAAAAAAAAAAogCgAAKCWKA83Nx+Nx53vs28nLh6buCOYAC9ovfjoHbG2y44ak+bW\\/Xhx4+mXy4+qY46i8WvVvW8v\\/6QenG+qTfifEvy34YxxneW7W7rFpFAAAAAAAAABQAAAcBUBHHk8512ccvfQSNRIsFVMp421GeT2oM8efpz38V6pdzbxO\\/Bn\\/5oO4CoACplPDnb3+XS1yzmvMQYz3vyxfK3LbICKiA6YYXO6Yk29fFh6cfu9g3JJNTpQVQBAUAAAEFBAAAAAAEVAFQBUVAAAAAAAAAAAAAAAFQBQAAAFQA0xyYzLHp0FR8\\/LG43VZern4tzc7eUAAFejhlk8T+16+nCX03brx5Zedbt\\/N6gPRMtf1x85fn4WY6827v5YxuOHeW8q3Lb1N\\/tUaEm\\/pQAAAAAAFRQAAAAcUaqAzXD5rvl4xrhAaiwiwVWOX2tufL8IOay6uzS6B6ePL1Y7beXiy9GX09U8qAFEYyrnndxrKuNyRUvfhCiCCrjLboHTgw3d3qPUxhj6MfS2KAKAKgigAAoAICKAgAAAAAIoAIoCCoAAAqKAAAAAAAAAACCgAAAKoACJZuPFzYenLb3OXLh6pQeIayx9NZAWZWdWooO\\/BZPOt3\\/APWXw9Msvzt5OK4y7s3+\\/h6sbLNwiNCeflVAAAAAAAAFEUAAHKotQGOS\\/wBK5YunNf6sYgsaRRRz5Pc6uOfnOoEa0kagMZTU268Ge56axn7K5Y243cB7ktTDL1Y7W9Kjjn9OV7dMvNcqyoIoD0cPH6Zu91jh4931Xp6AVUUUAAVFAAAAAAAABBQEAAAAAAAAAAAAAAAAAAAAAAAAAAAAABRFVBFAebmw3vw89mntznby546qDmoKNTKS+J5+3p488rNendeSTd709PDr\\/wAy37B3\\/aoqoAAAAAAAAKgCiAOdRagOXN8RMYnL5zi4orQKqDj\\/AOq7fDjO0VqNRIsBnl9ri68vUcwdOHP05a+Hoyvh49O8ztx0DGV1WK1lfzGPlAb48fVfpmTdenjx1Abxmo0AoqKAAAoAAqiKigIqAAACAACAAAAAAAAAAAAAAAAACKKJsBRDYKJs2CgAqKAgAKIojOXThnHornlPCDy5TTLryYuVAjvx8k8TVt\\/G9RwJLVHvx6\\/\\/ANacuCax+3RUUAAAAAAAAAAAHOotQHnz88lbx6Y7yv7dICgAZeMa44uvJ7K54g1GoixFc+XuMSNcvuSAaalsWF6BnKslXGeq6QdOLD5r0SaZxmo2KAAKgCqhtRRNpsGjbOzYNbGdmwaRnZsGhnZKDQmwFTaWgNCAKIAqAiqICAAAAAgou02m0yy0DWzbltm5eQdfUev86jj6qb8+QdfXvpZXH1p68r1BHf1Erju\\/NPUDvtZa4TJuZA67Xbl6mploHRCXYAqCAzW2bNwHLN58p5erLFxzxBxWWzovaA9XBMu7rTs8vDlq+cnqWIAKAAAAKIAoICgA51L1VrGfjC\\/oHHDt1nTlg7QAAGOX2M4rzfEMQaWIsRXHP30iZe+\\/tqA1Ey6WM5gw7cOPi1yk3Z9vVjNRBqdKkUUEANm0rNy\\/CjfqZuWu6xc039CN+qm2dmxWtm2NpaDdy+09bn4Ng6eqkzc1B19e09X4Y0TcB1mW2nKdtygtvk3pn5X5BqVWVQUBQAQAAATagM3aUF2bZUDbFvlcr4YvSBbtOu+y+E1aCKa0z8iNH6ieaoJs2i6tBfVGplGJNL4B03+KS2MTw1MgbmTe65yS9NeVHSVXKV0l2DQigzY5Zx2rGUB5M5qsu+ePlyy7qDfDjblvx\\/t6o8vFL6vGUn7eudLAAVAFBBdGgRTRoAFBBQHKufNdcddK5c\\/tk\\/IMccdWMHQEFIDjy++RqdM5+eRuICwOsao4fNajGLpEVqM5zy1Gc99AcUly\\/T0Ry4sdY7\\/LrEVVQAZuX4Ld\\/pjLOTrsC5a+6zv81JL3WpFRZ9TX7Zt\\/DVZoHk2zv8Q1fkF9US00AARBZpdIsFWLPKEUOq3Gb5WAvyvym1gCyoQGlZXYKIoAIgrKoCVGqxewPkEArLRoGNbpV0SAzZvpNePDpYaBz1qDWtroHPX4XVb0npEZ0sxb9EXUFY9Jpr9Q8gzuzpvHK\\/KaJBG7PwY5Wdnk77UdZfCueO\\/ltASxpmg55x5svFeux5+WaoMY69U3092OvTNTw8M7e3j16ZpYNAqoAAAAAAAAAA5OPP7sY7OHLd8uvwDWE8Ns4dNAEKA4d8l\\/bpHPDzla6wAy9lE5fHFUHHF0jGLcFajGfem4SyZdeQdMZ6ZI0ioozb8Fy05239AueTGMnd8rJvtrQJv8JtrR6QZNNaTQMla0vpBz0adPTDQMSLprRIozob0mkGWjSyAaPhQEUAVUUBUUBUAAAEAESqAyaXQCCgM6XSqDOjTQDPpNNAM6XSgM1NN6QE0aUBNJ\\/pVAxauMv\\/rykaioz03KWfhIDQCDOUceWWzy71zym8aDhhj6svN1r\\/69mE1jI8eNs3qPZh7JvtYKoKgAAAAAAAAADk82Xnly\\/b0\\/by4+ct\\/mg749NJOlBKXxjaJn446DlxR0Y454dAGOb2Rtz574xgrGLpGMG0FhPOaxqT5BqBCorNZuLdQGZKsjQCaFAQ0ugGdLIuhQBQZ0NIggoCC6ARQAAAVFAVAVQBAAEAFEVBAAAFBFAAAAAAABGkBBUoIAB4ajLUBrV13o1SftVRIoIpWK2xl9CPPf65168LvGb7eXL+ufl68fbFgoCoAAAAAAAAAA45eMLfp5+Pt35brirjxwHaKQBGebxxf7aY\\/ke3GfYJh02zj1GgHP+R7sZ9Os7cebzyoGDbGPTYrWPa73fwmPymIOkKCKgoACAogACgAACgIKgCKgCooIKAgAKIoAAqgoCKgIAAAAIogIAogCiKAACiGxVQAEUEZAAWIsBqNMStSiKAKJkqZKjz8vuenHqOHI7cW\\/+ObINgKgAAAACggKAADy\\/wAi64591nji\\/wAm+cYcc8A6QoU");
        return printModel;
    }

}
