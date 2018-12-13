package sample;

public class Model {

    public String dataProcess(String number1, String number2, String operator) {

        String result = "0";

        try {

            if(number1.contains(".") || number2.contains("."))
                result = calculateDouble(Double.parseDouble(number1), Double.parseDouble(number2), operator);
            else
                result = calculateLong(Long.parseLong(number1), Long.parseLong(number2), operator);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String calculateRoot(String number) {

        String root = "0";

        try {
            root = String.valueOf(Math.sqrt(Double.parseDouble(number)));
            if(root.endsWith(".0"))
                root = root.substring(0, root.length() - 2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }


    public String calculateLong(long number1, long number2, String operator){

        try {
            if (operator.equals("+")) {
                return String.valueOf(number1 + number2);
            }
            else if (operator.equals("-")) {
                return String.valueOf(number1 - number2);
            }
            else if (operator.equals("x")) {
                return String.valueOf(number1 * number2);
            }
            else if (operator.equals("/")) {
                if (number2 == 0)
                    return String.valueOf(0);

                if(number1 % number2 != 0)
                    return String.valueOf((double)number1 / (double)number2);

                return String.valueOf(number1 / number2);
            }

            System.out.println("Unknown operator - " + operator);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return String.valueOf(0);
    }

    public String calculateDouble(double number1, double number2, String operator) {

       try {

           if (operator.equals("+")) {
               if(String.valueOf(number1 + number2).endsWith(".0"))
                   return String.valueOf((long)(number1 + number2));

               return String.valueOf(number1 + number2);
           }
           else if (operator.equals("-")) {
               if (String.valueOf(number1 - number2).endsWith(".0"))
                   return String.valueOf((long)(number1 - number2));

               return String.valueOf(number1 - number2);
           }
           else if (operator.equals("x")) {
               if (String.valueOf(number1 * number2).endsWith(".0"))
                   return String.valueOf((long)(number1 * number2));

               return String.valueOf(number1 * number2);
           }
           else if (operator.equals("/")) {
               if (number2 == 0)
                   return String.valueOf(0);

               if (String.valueOf(number1 / number2).endsWith(".0"))
                   return String.valueOf((long)(number1 / number2));

               return String.valueOf( number1 / number2);
           }

//           System.out.println("Unknown operator - " + operator);
       }
       catch (Exception e) {
           e.printStackTrace();
       }

        return String.valueOf(0);
    }
}
