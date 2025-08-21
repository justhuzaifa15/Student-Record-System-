/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentrecordsystem1gui;

/**
 *
 * @author Huzaifa
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;



public class StudentRecordSystem1GUI {
    public static void main(String[] args) {
    JFrame frame=new JFrame("Student Record System"); 
    JPanel container=new JPanel(); 
    container.setBackground(new Color(245, 245, 245));
    container.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
    JPanel innerpanel=new JPanel();
    innerpanel.setLayout(null);
    innerpanel.setBackground(new Color(173, 216, 230));
    innerpanel.setPreferredSize(new Dimension(400,510));
    JLabel label=new JLabel("Welcome To Student Management System");
    label.setFont(new Font("Tahoma",Font.BOLD,18));
    label.setBounds(10, 0, 400, 70);
    innerpanel.add(label);
    JButton button1=new JButton("Add a Student");
    button1.setBounds(135, 100, 155, 40);
    innerpanel.add(button1);
    JButton button2=new JButton("View All Students");
     button2.setBounds(135, 155, 155, 40);
     innerpanel.add(button2);
    JButton button3=new JButton("Search Student by ID");
    button3.setBounds(135, 210, 155, 40);
     innerpanel.add(button3);
    JButton button4=new JButton("Delete Student by ID");
    button4.setBounds(135, 265, 155, 40);
     innerpanel.add(button4);
    JButton button5=new JButton("Exit"); 
    button5.setBounds(135, 325, 155, 40);
     innerpanel.add(button5);
    container.add(innerpanel);
    frame.add(container);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600,600);
    button1.setFocusPainted(false);
    button2.setFocusPainted(false);
    button3.setFocusPainted(false);
    button4.setFocusPainted(false);
    button5.setFocusPainted(false);
  frame.setLocationRelativeTo(null);
      button1.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e){
          JFrame addframe=new JFrame("Add Student");
          addframe.setSize(500, 500); 
          addframe.setLayout(new FlowLayout()); 
        
          addframe.setVisible(true);
          JPanel pan=new JPanel();
          pan.setLayout(null);
          pan.setPreferredSize(new Dimension(500,500));
          JLabel titlelabel=new JLabel("Enter Student Details");
          titlelabel.setBounds(115, 40, 300, 50);
          titlelabel.setFont(new Font("Tahoma",Font.BOLD,25));
          JLabel label=new JLabel("Enter Name:");  
          label.setBounds(100, 125, 100, 50);
          label.setFont(new Font("Tahoma",Font.BOLD,15));
          JTextField text1=new JTextField(15);
          text1.setBounds(210, 140,130, 22);
          JLabel idlabel=new JLabel("Enter ID:");
           idlabel.setBounds(115, 170, 100, 50);
          idlabel.setFont(new Font("Tahoma",Font.BOLD,15));
          
           JTextField text2=new JTextField(15);
          text2.setBounds(210, 185,130, 22);
          
          JLabel cgpalabel=new JLabel("Enter CGPA:");
           cgpalabel.setBounds(100, 220, 100, 50);
           
          cgpalabel.setFont(new Font("Tahoma",Font.BOLD,15));
           JTextField text3=new JTextField(15);
          text3.setBounds(210, 235,130, 22);
          JButton subtn=new JButton("Submit");
          subtn.setBounds(210, 290,130, 40);
          subtn.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
          {
          String name=text1.getText();
          String id=text2.getText();
          String  ecgpa=text3.getText();
     if(name.isEmpty() || id.isEmpty()||ecgpa.isEmpty()) {
          JOptionPane.showMessageDialog(null, "Name,ID or CGPA cannot be empty.");
    return;
}
          double cgpa;
          try{
             cgpa=Double.parseDouble(text3.getText());
  
          }
          catch(NumberFormatException s){
            JOptionPane.showMessageDialog(null, "Invalid CGPA. Please enter a numeric value.");
              return;
          }
        try{
            File check=new File("Student.txt");
             boolean flag=false;
             
            if(check.exists())
            {
               BufferedReader brr=new BufferedReader(new FileReader("Student.txt"));   
              String line;
              while ((line=brr.readLine()) != null)
              {
                String[] brline=line.split(",");
               if(brline[1].equals(id))
               { 
                   flag=true;
                 break;  
               }   
            } 
              brr.close();
            }
            if(flag==true)
            {
                JOptionPane.showMessageDialog(null, "ID Number Already Exist,Please Enter Different ID Number");
                return;
            }
             BufferedWriter br=new BufferedWriter(new FileWriter(check,true));
             br.write(name+","+id+","+cgpa);
             
             br.newLine();
         
           JOptionPane.showMessageDialog(null, "Student saved successfully!");
         br.close();
          text1.setText("");
          text2.setText("");
           text3.setText("");
          text1.requestFocus();
         }
         catch(IOException r){
             
             JOptionPane.showMessageDialog(null,"An error occurred:"+r.getMessage());
             
         } 
           
            }});
          pan.add(titlelabel);
          pan.add(text1);
          pan.add(label);
          pan.add(idlabel);
          pan.add(text2);
          pan.add(cgpalabel);
          pan.add(text3);
          pan.add(subtn);
     
          addframe.add(pan);
          addframe.setLocationRelativeTo(null);
          subtn.setFocusPainted(false);
        }

    });
button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame showframe = new JFrame("All Student Details");
                showframe.setLayout(new BorderLayout());

                ArrayList<Object[]> stddata = new ArrayList<>();

                try {
                    FileReader fr = new FileReader("Student.txt");
                    BufferedReader be = new BufferedReader(fr);

                    String line;
                           
                    while ((line = be.readLine()) != null) {
                        String[] spdata = line.split(",");
                        if (spdata.length >= 3) {
                           stddata.add(new Object[]{spdata[0],spdata[1],spdata[2]});
                        }
                    
                    }
                    be.close();
                } catch (IOException z) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + z.getMessage());
                }

                Object[][] tabdata = new Object[stddata.size()][3];
                for (int i = 0; i < stddata.size(); i++) {
                   tabdata[i]=stddata.get(i);
                }

                String[] Columns = {"Name", "ID", "CGPA" };
                JTable tbl = new JTable(tabdata, Columns);
                 tbl.setRowHeight(30);
                JScrollPane scroll = new JScrollPane(tbl);
                scroll.setPreferredSize(new Dimension(480, 300)); 

                showframe.add(scroll, BorderLayout.CENTER);
                showframe.setSize(500, 400);
                showframe.setLocationRelativeTo(null);
                showframe.setVisible(true);
            }
        });
        
       button3.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent n){
       JFrame searchframe=new JFrame("Search Student By ID");
       searchframe.setLayout(new FlowLayout());
      JPanel upsearchpanel=new JPanel();
      upsearchpanel.setLayout(null);
      JLabel selabel=new JLabel("Search Student By ID ");
      selabel.setFont(new Font("Tahoma",Font.BOLD,22));
      selabel.setBounds(80,5, 350, 70);
      upsearchpanel.setPreferredSize(new Dimension(400,210));
     
      JLabel lab2=new JLabel("Enter ID To Search");
      lab2.setFont(new Font("Tahoma",Font.BOLD,15));
      lab2.setBounds(5,90, 350, 70);
      JTextField setext=new JTextField(15);
      setext.setBounds(160,117,150,20);
      JButton sebutton=new JButton("Search");
      sebutton.setBounds(170,165,90, 35);
      
      
      JPanel downpanel=new JPanel();
      downpanel.setVisible(false);
      downpanel.setLayout(null);
      
      downpanel.setPreferredSize(new Dimension(400,210));
      JLabel tlabel=new JLabel("Student Details");
      tlabel.setFont(new Font("Arial",Font.BOLD,24));
      tlabel.setBounds(120, 10, 200, 30);
      
          JLabel name=new JLabel("Name");
          name.setBounds(80,70, 200, 30);
          name.setFont(new Font("Arial",Font.BOLD,15));
          JLabel id=new JLabel("ID");
          id.setBounds(105,100, 200, 30);
          id.setFont(new Font("Arial",Font.BOLD,15));
          
          JLabel CGPA=new JLabel("CGPA");
          CGPA.setBounds(80,130, 200, 30);
          CGPA.setFont(new Font("Arial",Font.BOLD,15));
      sebutton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent a){
              boolean flag=false;
              downpanel.setVisible(false);
              
          try
          {
           FileReader seread=new FileReader("Student.txt");
           BufferedReader sebr=new BufferedReader(seread);
           String line;
           
           while((line=sebr.readLine())!=null){
               String[]seline=line.split(",");
               if(seline[1].equals(setext.getText())){
                   flag=true;
             downpanel.setVisible(true);
             name.setText("Student Name:"+"  "+seline[0]);
             id.setText("Student ID:"+"  "+seline[1]); 
             CGPA.setText("Student CGPA:"+"  "+seline[2]);  
                   break;
               }  
           }
           if(flag==false){
               JOptionPane.showMessageDialog(null,"Student Doesn't Found");
               return;
           }
           sebr.close();
          }
          catch(IOException e){
               e.printStackTrace();
          }
          }   
      });
       upsearchpanel.add(lab2);
       upsearchpanel.add(setext);
       upsearchpanel.add(selabel);
       upsearchpanel.add(sebutton);
       downpanel.add(tlabel);
       downpanel.add(name);
        downpanel.add(id);
         downpanel.add(CGPA);
       searchframe.add(upsearchpanel);
       searchframe.add(downpanel);
       sebutton.setFocusPainted(false);
            
       searchframe.setVisible(true);
       searchframe.setSize(new Dimension(500,500));
       searchframe.setLocationRelativeTo(null);
       }});
       
       
       
       button4.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent k){
       JFrame deleteframe=new JFrame("Delete Student By ID");
       deleteframe.setLayout(new FlowLayout());
      JPanel updelpanel=new JPanel();
      updelpanel.setLayout(null);
      
      JLabel delabel=new JLabel("Delete Student By ID ");
      delabel.setFont(new Font("Tahoma",Font.BOLD,22));
      delabel.setBounds(80,5, 350, 70);
      updelpanel.setPreferredSize(new Dimension(400,210));
     
      JLabel lab2=new JLabel("Enter ID To Delete");
      lab2.setFont(new Font("Tahoma",Font.BOLD,15));
      lab2.setBounds(5,90, 350, 70);
      JTextField setext=new JTextField(15);
      setext.setBounds(160,117,150,20);
      JButton sebutton=new JButton("Search");
      sebutton.setBounds(170,165,90, 35);
      
      JButton delbutton=new JButton("Delete");
      delbutton.setBounds(170, 165, 90,35);
      JPanel downpanel=new JPanel();
      downpanel.setVisible(false);
      downpanel.setLayout(null);
      
      downpanel.setPreferredSize(new Dimension(400,210));
      JLabel tlabel=new JLabel("Student Details");
      tlabel.setFont(new Font("Arial",Font.BOLD,24));
      tlabel.setBounds(120, 10, 200, 30);
      
          JLabel name=new JLabel("Name");
          name.setBounds(80,70, 200, 30);
          name.setFont(new Font("Arial",Font.BOLD,15));
          JLabel id=new JLabel("ID");
          id.setBounds(105,100, 200, 30);
          id.setFont(new Font("Arial",Font.BOLD,15));
          
          JLabel CGPA=new JLabel("CGPA");
          CGPA.setBounds(80,130, 200, 30);
          CGPA.setFont(new Font("Arial",Font.BOLD,15));
          sebutton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent a){
              boolean flag=false;
              downpanel.setVisible(false);
              
          try
          {
           FileReader deread=new FileReader("Student.txt");
           BufferedReader debr=new BufferedReader(deread);
           String line;
           
           while((line=debr.readLine())!=null){
               String[]deline=line.split(",");
               if(deline[1].equals(setext.getText())){
                   flag=true;
             downpanel.setVisible(true);
             name.setText("Student Name:"+"  "+deline[0]);
             id.setText("Student ID:"+"  "+deline[1]); 
             CGPA.setText("Student CGPA:"+"  "+deline[2]);  
                   break;
               }      
           }
           if(flag==false){
               JOptionPane.showMessageDialog(null,"Student Doesn't Found");
               return;
           }
             
           debr.close();
          }
          catch(IOException e){
               e.printStackTrace();
          }
       
          }   

      });
     delbutton.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent m){
           String delid=setext.getText();
           File bfred=new File("Student.txt");
           File bfwri=new File("temp.txt");
         boolean del=false;
         try{
             BufferedReader read=new BufferedReader(new FileReader("Student.txt"));
              BufferedWriter writee=new BufferedWriter(new FileWriter("temp.txt"));
              
              String line;
              
           while((line=read.readLine())!=null)
           {
               String [] spline=line.split(",");
               if(delid.equals(spline[1])){
                   del=true;
                   continue;
               }
              writee.write(line);
              writee.newLine();  
           }   
            read.close();
            writee.close();
         }  
           catch(IOException n){
              n.printStackTrace();   
           }
          if(del){
             bfred.delete();
             bfwri.renameTo(bfred);
             JOptionPane.showMessageDialog(null,"Student Deleted Successfully");
             downpanel.setVisible(false);
             setext.setText(" ");  
          }
          else
          {
              bfwri.delete();
             JOptionPane.showMessageDialog(null,"Student Not Found");
          }
         }
             });

               
       updelpanel.add(lab2);
       updelpanel.add(setext);
       updelpanel.add(delabel);
       updelpanel.add(sebutton);
       downpanel.add(tlabel);
       downpanel.add(name);
        downpanel.add(id);
         downpanel.add(CGPA);
         downpanel.add(delbutton);
       deleteframe.add(updelpanel);
       deleteframe.add(downpanel);
       delbutton.setFocusPainted(false);
            
       deleteframe.setVisible(true);
       deleteframe.setSize(new Dimension(500,500));
       deleteframe.setLocationRelativeTo(null);     
       
       }});
       
  button5.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent b){
         System.exit(0);
    
     } 
        
  });
       

    }
}
