import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {

    private JButton refresh = new JButton("Refresh");
    private JButton getPage = new JButton("Get page");
    private JButton exitButton = new JButton("Exit");
    private OfferList pepperek = new OfferList();
    private JTable table;
    private String[] columnNames = {"Name", "Temperature"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
    private JTextField pageInsert = new JTextField();
    private String site = "https://www.pepper.pl/";


    public Window() {
        super("PepperOffers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,400);
        setLocation(50,50);
        setVisible(true);
        setLayout(new BorderLayout());

        pepperek.downloadPage(site);
        add(pageInsert, BorderLayout.PAGE_START);
        add(refresh, BorderLayout.LINE_START);
        refresh.addActionListener(this::actionPerformed);
        add(getPage, BorderLayout.CENTER);
        getPage.addActionListener(this::actionPerformed);
        add(exitButton, BorderLayout.LINE_END);
        exitButton.addActionListener(this::actionPerformed);
        table = new JTable(tableModel);
        add(table, BorderLayout.PAGE_END);

        puttingData();
        System.out.println(tableModel.getRowCount() + " number of rows");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(e.getSource() == refresh) {
            removeData();
            puttingData();
        } else if(e.getSource() == getPage) {
            String text = pageInsert.getText();
            int bound = Integer.parseInt(text);
            System.out.println("pushed and got: " + text );
            if(text == null) {
                System.out.println("Please enter data");
            } else if (bound > 0 && bound < 301) {
                pepperek.downloadPage("https://www.pepper.pl/?page=" + bound);
                removeData();
                puttingData();
            } else {
                System.out.println("Wrong input!");
            }
        } else if (e.getSource() == exitButton) {
            //Cruel but effective
            System.exit(0);
        }
    }

    public void removeData() {
        if(!tableModel.getValueAt(1,1).equals(null)) {
            int tableSize = tableModel.getRowCount();
            for(int i = 0; i < tableSize; ++i) {
                tableModel.removeRow(0);
            }
        }
    }

    public void puttingData() {
        try {
            pepperek.showList();
            ArrayList<Offer> offerList = pepperek.getList();
            String name;
            String temp;
            for (Offer offer : offerList) {
                name = offer.getOfferName();
                temp = offer.getOfferTemp();
                Object[] data = {name, temp};
                tableModel.addRow(data);
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
