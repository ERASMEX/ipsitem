package com.iph.view;

import com.iph.model.User;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
// ... otros imports

public class GestionUsuariosDialog extends JDialog {
    // ... campos y constructor

    private void guardarUsuario() {
        // ... validaciones

        // CORRECCIÓN: Usar constructor sin argumentos y setters
        // Soluciona: constructor User in class com.iph.model.User cannot be applied...
        User user = new User();
        user.setUsername("valor_del_campo_usuario"); // Sustituir por el JTextfield real
        user.setEmail("valor_del_campo_email");     // Sustituir por el JTextfield real
        user.setRol("valor_del_campo_rol");         // Sustituir por el JComboBox real

        // ... Lógica para llamar a UserService.registerUser(user, password)
        JOptionPane.showMessageDialog(this, "Usuario guardado.");
    }
    // ... el resto de GestionUsuariosDialog
}