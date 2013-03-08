
package org.peidevs.waro;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

class MainPanel extends JPanel {
  JTextPane codePane = new JTextPane();

  MainPanel() {
    add(new JScrollPane(codePane));
    codePane.addKeyListener(new KeyHandler());
  }

  private class KeyHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent ev){
      if (ev.getKeyCode() == KeyEvent.VK_SPACE) {
        System.out.println("pressed space");
      } else {
        System.out.println("pressed char");
      }
    }
    @Override
    public void keyReleased(KeyEvent ev){
      if (ev.getKeyCode() == KeyEvent.VK_SPACE) {
        System.out.println("released space");
    } else {
        System.out.println("released char");
    }
    }
  }

  public static void main(String... args) {
    JFrame f = new JFrame();
    MainPanel m = new MainPanel();
    f.add(m);
    f.pack();
    f.setVisible(true);
  }

}
