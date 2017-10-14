/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.Color;

/**
 *
 * @author Dhitiwat
 */
public class Counter {

    static public Table[] table = new Table[12];
    static public CounterGui gui;
    static public NotificationGui[] notiBar = new NotificationGui[12];
    static public Boolean[] notiBarStatus = new Boolean[12];
    static public int order = 10;

    public Counter() {
    }

    public Table getTableInfo(int n) {

        return table[n - 1];
    }

    static public void respondToCommand(Service demand) {
        System.out.println(demand.tableNumber);
        if (demand.getTypeOfService().equals("Setup")) {
            SetupNewTable set = (SetupNewTable) demand;
            if (set.getTypeOfSetup().equals("Coming In")) {
                Counter.table[set.tableNumber - 1] = new Table(set.getTableInfo(),++order);
                gui.setTableButtonColor(set.tableNumber, new Color(192, 226, 202));
            } else if (set.getTypeOfSetup().equals("Leaving")) {
                /*
                Counter.table[set.tableNumber - 1].reset();
                Counter.table[set.tableNumber - 1] = null;
                gui.setTableButtonColor(set.tableNumber, new Color(232,224,224));
                 */
                Counter.table[set.tableNumber - 1].checkingBill = true;
                gui.setTableButtonColor(set.tableNumber, Color.red);

            }

        } else if (demand.getTypeOfService().equals("Message")) {
            Message mes = (Message) demand;
            System.out.println(notiBarStatus[mes.tableNumber - 1]);
            gui.setTableButtonColor(mes.tableNumber, new Color(244, 133, 142));
            if (notiBarStatus[mes.tableNumber - 1] == true) {
                notiBar[mes.tableNumber - 1].setVisible(false);
                notiBar[mes.tableNumber - 1].dispose();
            }

            notiBarStatus[mes.tableNumber - 1] = true;

            notiBar[mes.tableNumber - 1] = new NotificationGui(mes.tableNumber, mes.messageType,gui.getLocationOnScreen());
            notiBar[mes.tableNumber - 1].setVisible(true);

        } else if (demand.getTypeOfService().equals("Order")) {
            Order or = (Order) demand;
            for (Food i : or.foodOrder.keySet()) {
                System.out.println("yoyo\t\t" + i + "\t" + or.foodOrder.get(i));
            }
            Counter.table[or.tableNumber - 1].addOrderd(or.foodOrder, or.drinksOrder);
            gui.updateBillByTable(or.tableNumber);
            gui.chosenTable = or.tableNumber;
        } else {
            System.out.println("Unknown Service");
        }

    }

    static public void setTime(String newTime) {
        gui.upDateTime(newTime);
    }

    static public void setGuiTableButtonColor(int tableNumber, Color c) {
        gui.setTableButtonColor(tableNumber, c);
    }

}
