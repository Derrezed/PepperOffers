import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {

    private boolean flag = false;
    private JButton getAnswer = new JButton("Refresh");
    private OfferList pepperek = new OfferList("https://www.pepper.pl/");
    private JTable table;
    private String[] columnNames = {"Name", "Temperature"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);

    public Window() {
        super("PepperOffers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,800);
        setLocation(50,50);
        setVisible(true);
        setLayout(new GridLayout(2,2));
        add(getAnswer);
        getAnswer.addActionListener(this::actionPerformed);
        table = new JTable(tableModel);
        add(table);
        puttingData();
        System.out.println(tableModel.getRowCount() + " ilosc wierszy");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == getAnswer) {
            if(!tableModel.getValueAt(1,1).equals(null)) {
                int tableSize = tableModel.getRowCount();
                for(int i = 0; i < tableSize; ++i) {
                    tableModel.removeRow(0);
                }
                puttingData();
            }
        }
    }

    public boolean puttingData() {
        try {
            pepperek.showList();
            ArrayList<Offer> offerList = pepperek.getList();
            int row = 0;
            String name;
            String temp;
            for (Offer offer : offerList) {
                name = offer.getOfferName();
                temp = offer.getOfferTemp();
                Object[] data = {name, temp};
                tableModel.addRow(data);
                row++;
            }
            return true;
        } catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }
}
