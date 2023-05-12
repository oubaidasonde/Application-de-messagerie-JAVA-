package javaswingdev.form;

import javaswingdev.card.ModelCard;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.mail.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.mail.MessagingException;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import javax.mail.internet.InternetAddress;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class Inbox extends javax.swing.JPanel {
final String username = "javaproject.test02@gmail.com";
    final String password = "mhzrnlclycvjkbrs";
    final String receiverEmail = "javaproject.test02@gmail.com";
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailSMTPPort = "587";
    final String mailStoreType = "pop3s";
    Message[] messages;
    public Inbox() throws MessagingException, AWTException {
        initComponents();
        init();
    }

    private void init()throws AWTException, MessagingException{
       
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailSMTPPort);

        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);

            Store mailStore = session.getStore(mailStoreType);
            mailStore.connect(emailSMTPserver, username, password);

            Folder folder = mailStore.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            messages = folder.getMessages();
            System.out.println("Total Message - " + messages.length);

            BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
            table.fixTable(jScrollPane1);
            
            for (int i = messages.length-1; i< messages.length; i--) {
              Message emailMessage = messages[i];

               /* Message emailMessage = messages[i];*/
                InternetAddress   sender=(InternetAddress)emailMessage.getFrom()[0];
              
                table.addRow(new Object[]{sender.getPersonal(),emailMessage.getFrom()[0] ,emailMessage.getSubject(),CheckingMails.getMessageContent(emailMessage), "25 Apr,2018"});
            
                
            }
            
                /****** email content *****/
        
        
        /*table.addRow(new Object[]{emailMessage.getFrom()[0], emailMessage.getSubject(), CheckingMails.getMessageContent(emailMessage), "Admin", "25 Apr,2018"});*/
        
        
        
        /******old methode*****/
        
         
        
       /* 
        table.addRow(new Object[]{"3", "Ross Kopelman", "rosskopelman@gmail.com", "Subscriber", "25 Apr,2018"});
        table.addRow(new Object[]{"4", "Mike Hussy", "mikehussy@gmail.com", "Admin", "25 Apr,2018"});
        table.addRow(new Object[]{"5", "Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018"});
        table.addRow(new Object[]{"6", "Andrew Strauss", "andrewstrauss@gmail.com", "Editor", "25 Apr,2018"});
        table.addRow(new Object[]{"7", "Ross Kopelman", "rosskopelman@gmail.com", "Subscriber", "25 Apr,2018"});
        table.addRow(new Object[]{"8", "Mike Hussy", "mikehussy@gmail.com", "Admin", "25 Apr,2018"});
        table.addRow(new Object[]{"9", "Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018"});
        table.addRow(new Object[]{"10", "Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018"});
        table.addRow(new Object[]{"11", "Andrew Strauss", "andrewstrauss@gmail.com", "Editor", "25 Apr,2018"});
        table.addRow(new Object[]{"12", "Ross Kopelman", "rosskopelman@gmail.com", "Subscriber", "25 Apr,2018"});
        table.addRow(new Object[]{"13", "Mike Hussy", "mikehussy@gmail.com", "Admin", "25 Apr,2018"});
        table.addRow(new Object[]{"14", "Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018"});*/
/***********************old code to print messages*****************************************************/
              /* System.out.println();
                System.out.println("Email " + (i+1) + " -");
                System.out.println("Subject - " + emailMessage.getSubject());
                System.out.println("From - " + emailMessage.getFrom()[0]);
                System.out.println("Content - " + CheckingMails.getMessageContent(emailMessage));

                /**************display trash********/
              /*
                SystemTray tray = SystemTray.getSystemTray();

                //If the icon is a file
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                //Alternative (if the icon is on the classpath):
                //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

                TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
                //Let the system resize the image if needed
                trayIcon.setImageAutoSize(true);
                //Set tooltip text for the tray icon
                trayIcon.setToolTip("System tray icon demo");
                tray.add(trayIcon);


                trayIcon.displayMessage(emailMessage.getSubject(),CheckingMails.getMessageContent(emailMessage), MessageType.INFO);**/


            

            /***
            System.out.print("Enter email number to " +
                    "which you want to forward: ");

            String emailNo = reader.readLine();

            Message emailMessage =
                    folder.getMessage(Integer.parseInt(emailNo));

            Message mimeMessage = new MimeMessage(session);
            mimeMessage = (MimeMessage) emailMessage.reply(false);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setSubject("Fwd: " + mimeMessage.getSubject());
           *
            mimeMessage.setText(CheckingMails.getMessageContent(emailMessage));
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmail));

            Transport.send(mimeMessage);
            System.out.println("Email message " +
                    "forwarded successfully.");

            folder.close(false);
            mailStore.close();*/
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in forwarding email.");
        }
    }

    private class SMTPAuthenticator extends
            Authenticator {
        public PasswordAuthentication
        getPasswordAuthentication() {
            return new PasswordAuthentication(username,
                    password);
        }
    }
     
        
        
       

        //  init card data
        /*card1.setData(new ModelCard(null, null, null, "$ 500.00", "Report Income Monthly"));
        card2.setData(new ModelCard(null, null, null, "$ 800.00", "Report Expense Monthly"));
        card3.setData(new ModelCard(null, null, null, "$ 300.00", "Report Profit Monthly"));*/
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        jTextField1 = new javax.swing.JTextField();

        setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "From", "Email", "Subject", "Content", "Date Join"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
 
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(table.getModel());
 table.setRowSorter(rowSorter);
  rowSorter.setRowFilter(RowFilter.regexFilter(jTextField1.getText().trim()));
 // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(table.getModel());
 table.setRowSorter(rowSorter);
 
 rowSorter.setRowFilter(RowFilter.regexFilter(jTextField1.getText().trim()));
 
    }//GEN-LAST:event_jTextField1KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table;
    // End of variables declaration//GEN-END:variables
}
