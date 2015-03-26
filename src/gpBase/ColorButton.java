package gpBase;

public class ColorButton extends javax.swing.JRadioButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private colorPicker _cp;
	private java.awt.Color _c;
	ColorButton(java.awt.Color c,colorPicker cp,javax.swing.ButtonGroup Group,boolean isSelected){
			super("",isSelected);
			_cp=cp;
			_c=c;
			this.setBackground(_c);
			this.addActionListener(_cp);
	}

}
