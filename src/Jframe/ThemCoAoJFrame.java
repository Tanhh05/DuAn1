/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import main.repository.CoAoRepository;
import main.repository.ThuocTinhSanPhamRepository;
import main.entity.CoAo;
import main.entity.ThuocTinhSanPham;
import com.Product.form.SanPhamForm;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ThemCoAoJFrame extends javax.swing.JFrame {
     private DefaultTableModel dtmThuocTinhSanPham;
    private ThuocTinhSanPhamRepository thuocTinhSanPhamRepository = new ThuocTinhSanPhamRepository();
    private CoAoRepository coao = new CoAoRepository();

    /**
     * Creates new form ThemCoAoJFrame
     */
    public ThemCoAoJFrame() {
        initComponents();
        setTitle("Thêm Cổ Áo");
        setLocationRelativeTo(null);
       
    }
    
//    private String capitalizeFirstLetter(String input) {
//    String[] words = input.trim().split("\\s+");
//    StringBuilder capitalizedText = new StringBuilder();
//    for (String word : words) {
//        if (word.length() > 0) {
//            capitalizedText.append(word.substring(0, 1).toUpperCase())
//                           .append(word.substring(1).toLowerCase())
//                           .append(" ");
//        }
//    }
//    return capitalizedText.toString().trim();
//}
//    
//    private void setupKeyListener() {
//    txt_TenCoAo.addKeyListener(new KeyAdapter() {
//        @Override
//        public void keyReleased(KeyEvent e) {
//            String text = capitalizeFirstLetter(txt_TenCoAo.getText());
//            txt_TenCoAo.setText(text);
//        }
//    });
//}

    
     private ThuocTinhSanPham getFormDataThuocTinhSP() {
        if (txt_TenCoAo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên thuộc tính", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        ThuocTinhSanPham ttsp = new ThuocTinhSanPham();
        ttsp.setMaThuocTinhSanPham(txt_MaCoAo.getText());
        ttsp.setTenThuocTinhSanPham(txt_TenCoAo.getText());

        return ttsp;
    }

    private void showTableThuocTinhSanPham(ArrayList<ThuocTinhSanPham> lists) {
        dtmThuocTinhSanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmThuocTinhSanPham.addRow(new Object[]{
            index.getAndIncrement(), s.getMaThuocTinhSanPham(), s.getTenThuocTinhSanPham(),}));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_MaCoAo = new com.Product.GUI.textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        txt_TenCoAo = new com.Product.GUI.textfield.TextField();
        buttonBadges1 = new com.Product.swing.ButtonBadges();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txt_MaCoAo.setText("###");
        txt_MaCoAo.setEnabled(false);
        txt_MaCoAo.setLabelText("Mã Cổ Áo");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thêm Cổ Áo");

        txt_TenCoAo.setLabelText("Tên Cổ Áo");

        buttonBadges1.setBackground(new java.awt.Color(255, 153, 0));
        buttonBadges1.setText("Thêm");
        buttonBadges1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonBadges1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBadges1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_TenCoAo, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addComponent(txt_MaCoAo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(txt_MaCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_TenCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBadges1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBadges1ActionPerformed
        // TODO add your handling code here:
         ThuocTinhSanPham ttsp = getFormDataThuocTinhSP();
        if (ttsp != null) {
            if(thuocTinhSanPhamRepository.insertCoAo(ttsp)) {
                JOptionPane.showMessageDialog(null, "Thêm thuộc tính sản phẩm thành công");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
                this.dispose();
            }
        }


    }//GEN-LAST:event_buttonBadges1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemCoAoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemCoAoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemCoAoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemCoAoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemCoAoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.swing.ButtonBadges buttonBadges1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private com.Product.GUI.textfield.TextField txt_MaCoAo;
    private com.Product.GUI.textfield.TextField txt_TenCoAo;
    // End of variables declaration//GEN-END:variables
}