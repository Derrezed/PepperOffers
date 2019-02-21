import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {

    private boolean flag = false;
    private JButton refresh = new JButton("Refresh");
    private JButton getAnswer = new JButton("Refresh");
    private OfferList pepperek; //= new OfferList("https://www.pepper.pl/");
    private JTable table;
    private String[] columnNames = {"Name", "Temperature"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
    private JTextField pageInsert;

    public Window() {
        super("PepperOffers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,400);
        setLocation(50,50);
        setVisible(true);
        setLayout(new BorderLayout());
        add(refresh, BorderLayout.LINE_START);
        refresh.addActionListener(this::actionPerformed);
        table = new JTable(tableModel);
        pageInsert = new JTextField();
        add(pageInsert, BorderLayout.PAGE_START);
        add(table, BorderLayout.PAGE_END);
        puttingData();
        System.out.println(tableModel.getRowCount() + " ilosc wierszy");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == refresh) {
            if(!tableModel.getValueAt(1,1).equals(null)) {
                int tableSize = tableModel.getRowCount();
                for(int i = 0; i < tableSize; ++i) {
                    tableModel.removeRow(0);
                }
                puttingData();
            }
        }
    }

    public void puttingData() {
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
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
