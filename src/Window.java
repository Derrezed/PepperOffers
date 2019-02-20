import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {

    private JButton getAnswer = new JButton("Get results");
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == getAnswer) {
            pepperek.showList();
            ArrayList<Offer> offerList =  pepperek.getList();
            int row = 0;
            String name;
            String temp;
            for(Offer offer : offerList) {
                name = offer.getOfferName();
                temp = offer.getOfferTemp();
                Object[] data = {name,temp};
                tableModel.addRow(data);
                row++;
            }
        }
    }
}
