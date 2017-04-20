/*
 * 	Szymon Terlecki, 01.12.2016r.
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class CompareCollections extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 1L;

	Vector<Room> vector = new Vector<Room>();
	ArrayList<Room> arrayList = new ArrayList<Room>();
	LinkedList<Room> linkedList = new LinkedList<Room>();
	HashSet<Room> hashSet = new HashSet<Room>();
	TreeSet<Room> treeSet = new TreeSet<Room>();
	
	CollectionView vectorView;
	CollectionView arrayListView;
	CollectionView linkedListView;
	CollectionView hashSetView;
	CollectionView treeSetView;
	
	JLabel tagBuilding = new JLabel("    Budynek: ");
	JTextField fieldBuilding = new JTextField(10);
	JLabel tagRoom = new JLabel("Pokoj: ");
	JTextField fieldRoom = new JTextField(10);
	JLabel tagDescription = new JLabel("Opis: ");
	JTextField fieldDescription = new JTextField(10);
	JButton buttonAdd = new JButton("Dodaj");
	JButton buttonRemove = new JButton("Usun");
	JButton buttonClear = new JButton("Wyczysc");
	JButton buttonSort = new JButton("Sortuj");
	JButton buttonAuthor = new JButton("Autor");
	
	public CompareCollections()
	{
		super("Program ukazujacy dzialanie kolekcji z wykorzystaniem schematu pokoi i budynkow Politechniki Wroclawskiej.");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900,500);
		
		JPanel panel = new JPanel();
		panel.add(tagBuilding);
		panel.add(fieldBuilding);
		panel.add(tagRoom);
		panel.add(fieldRoom);
		panel.add(tagDescription);
		panel.add(fieldDescription);
		
		buttonAdd.addActionListener(this);
		panel.add(buttonAdd);
		buttonRemove.addActionListener(this);
		panel.add(buttonRemove);
		buttonClear.addActionListener(this);
		panel.add(buttonClear);
		buttonAuthor.addActionListener(this);
		panel.add(buttonAuthor);
		buttonSort.addActionListener(this);
		panel.add(buttonSort);
		
		vectorView = new CollectionView(vector, 250, 200, "vector:");
		panel.add(vectorView);
		
		arrayListView = new CollectionView(arrayList, 250, 200, "array list:");
		panel.add(arrayListView);
		
		linkedListView = new CollectionView(linkedList, 250, 200, "linked list:");
		panel.add(linkedListView);
		
		hashSetView = new CollectionView(hashSet, 250, 200, "hash set:");
		panel.add(hashSetView);
		
		treeSetView = new CollectionView(treeSet, 250, 200, "tree set:");
		panel.add(treeSetView);
		
		setContentPane(panel);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		String building;
		String room;
		String description;
		Object source = evt.getSource();
		
		if(source == buttonAdd)
		{
			building = fieldBuilding.getText();
			room = fieldRoom.getText();
			description = fieldDescription.getText();
			
			if(!(building.equals("") || room.equals("") || description.equals("")))
			{
				Room x = new Room(building,Integer.parseInt(room),description);
				vector.add(x);
				arrayList.add(x);
				linkedList.add(x);
				hashSet.add(x);
				treeSet.add(x);
			}
		}
		else if(source == buttonClear)
		{
			vector.clear();
			arrayList.clear();
			linkedList.clear();
			hashSet.clear();
			treeSet.clear();
		}
		else if(source == buttonRemove)
		{
			building = fieldBuilding.getText();
			room = fieldRoom.getText();
			Room x = new Room(building,Integer.parseInt(room),"");
			vector.remove(x);
			arrayList.remove(x);
			linkedList.remove(x);
			hashSet.remove(x);
			treeSet.remove(x);
			
			// dzia³aja oba algorytmy
			/*
			Iterator<Room> iterVector = vector.iterator();
			while(iterVector.hasNext())
			{
				Room e = iterVector.next();
				if(e.equals(x)) 
				{
					iterVector.remove();
					break;
				}
			}
			
			Iterator<Room> iterArrayList = arrayList.iterator();
			while(iterArrayList.hasNext())
			{
				Room e = iterArrayList.next();
				if(e.equals(x)) 
				{
					iterArrayList.remove();	
					break;
				}
			}
			
			Iterator<Room> iterLinkedList = linkedList.iterator();
			while(iterLinkedList.hasNext())
			{
				Room e = iterLinkedList.next();
				if(e.equals(x)) 
				{
					iterLinkedList.remove();
					break;
				}
			}
			
			Iterator<Room> iterHashSet = hashSet.iterator();
			while(iterHashSet.hasNext())
			{
				Room e = iterHashSet.next();
				if(e.equals(x)) 
				{
					iterHashSet.remove();
					break;
				}
			}
			
			Iterator<Room> iterTreeSet = treeSet.iterator();
			while(iterTreeSet.hasNext())
			{
				Room e = iterTreeSet.next();
				if(e.equals(x)) 
				{
					iterTreeSet.remove();	
					break;
				}
			}
			*/
		}
	
		
		else if(source == buttonAuthor)
		{
			JOptionPane.showMessageDialog(this, "Autor: Szymon Terlecki\n01.12.2016r.");
		}
		
		else if(source == buttonSort)
		{
			Collections.sort(vector);
			Collections.sort(arrayList);
			Collections.sort(linkedList);
		}
			
		vectorView.refresh();
		arrayListView.refresh();
		linkedListView.refresh();
		hashSetView.refresh();
		treeSetView.refresh();			
		
	}
		
		
	public static void main(String[] args)
	{
		new CompareCollections();
	}
}
	


class CollectionView extends JScrollPane
{
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private DefaultTableModel tableModel;
	private Collection<Room> collection;
	
	CollectionView(Collection<Room> collection, int width, int height, String description)
	{
		String[] column = {"Budynek:","Pokoj:","Opis:"};
		tableModel = new DefaultTableModel(column,0);
		table = new JTable(tableModel);
		table.setRowSelectionAllowed(false);
		this.collection = collection;
		setViewportView(table);
		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createTitledBorder(description));
	}
	
	void refresh()
	{
		tableModel.setRowCount(0);
		for(Room content : collection)
		{	
			String[] line = {content.getSymbol(), ((Integer)content.getNumber()).toString(), content.getDescription()};
			tableModel.addRow(line);
		}
	}
	
}