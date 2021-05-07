/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author vpc
 */
public class CanvasTextFieldEditor extends JTextField
        implements ActionListener, FocusListener, MouseListener, DocumentListener {

    public CanvasTextFieldEditor() {
        setOpaque(false);
        setColumns(1);
        setSize(getPreferredSize());
        setColumns(0);
        addActionListener(this);
        addFocusListener(this);
        addMouseListener(this);
        getDocument().addDocumentListener(this);

//        setBorder(null);
//        setOpaque(true);
    }

    public void actionPerformed(ActionEvent e) {
        setEditable(false);
    }

    public void focusLost(FocusEvent e) {
        setEditable(false);
    }

    public void focusGained(FocusEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            setEditable(true);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void insertUpdate(DocumentEvent e) {
        updateSize();
    }

    public void removeUpdate(DocumentEvent e) {
        updateSize();
    }

    public void changedUpdate(DocumentEvent e) {
    }

    private void updateSize() {
        setSize(getPreferredSize());
    }

}
