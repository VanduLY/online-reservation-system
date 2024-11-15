import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReservationSystem {
    private JFrame frame;
    private JComboBox<String> dateBox;
    private JComboBox<String> timeSlotBox;
    private JTextArea reservationArea;
    private ArrayList<String> reservations;

    public ReservationSystem() {
        reservations = new ArrayList<>();
        createUI();
    }

    private void createUI() {
        frame = new JFrame("Online Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Panel for reservation form
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel dateLabel = new JLabel("Select Date:");
        JLabel timeSlotLabel = new JLabel("Select Time Slot:");
        dateBox = new JComboBox<>(generateDates());
        timeSlotBox = new JComboBox<>(new String[]{
            "9:00 AM - 10:00 AM", 
            "10:00 AM - 11:00 AM", 
            "11:00 AM - 12:00 PM", 
            "1:00 PM - 2:00 PM", 
            "2:00 PM - 3:00 PM"
        });

        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(this::handleReservation);

        formPanel.add(dateLabel);
        formPanel.add(dateBox);
        formPanel.add(timeSlotLabel);
        formPanel.add(timeSlotBox);
        formPanel.add(new JLabel()); // Placeholder
        formPanel.add(reserveButton);

        frame.add(formPanel, BorderLayout.NORTH);

        // Panel for displaying reservations
        reservationArea = new JTextArea();
        reservationArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reservationArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Reservations"));

        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private String[] generateDates() {
        LocalDate today = LocalDate.now();
        String[] dates = new String[7]; // Next 7 days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 7; i++) {
            dates[i] = today.plusDays(i).format(formatter);
        }
        return dates;
    }

    private void handleReservation(ActionEvent e) {
        String date = (String) dateBox.getSelectedItem();
        String timeSlot = (String) timeSlotBox.getSelectedItem();
        String reservation = "Date: " + date + ", Time: " + timeSlot;

        if (reservations.contains(reservation)) {
            JOptionPane.showMessageDialog(frame, "This time slot is already reserved.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            reservations.add(reservation);
            reservationArea.append(reservation + "\n");
            JOptionPane.showMessageDialog(frame, "Reservation confirmed!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReservationSystem::new);
    }
}
