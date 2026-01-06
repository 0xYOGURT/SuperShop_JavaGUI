// package Data;

// import javax.swing.JTable;
// import javax.swing.table.TableModel;
// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;

// public class FileManager {

//     public static void saveBill(String name, String contact, JTable table) {

//         try {
//             File dir = new File("Data/info");
//             if (!dir.exists()) dir.mkdirs();

//             File file = new File(dir, "sales.txt");
//             FileWriter fw = new FileWriter(file, true);

//             fw.write("Customer Name: " + name + "\n");
//             fw.write("Contact: " + contact + "\n");
//             fw.write("---------------------------------\n");

//             TableModel model = table.getModel();
//             for (int i = 0; i < model.getRowCount(); i++) {
//                 fw.write(
//                         model.getValueAt(i, 0) + " | " +
//                         model.getValueAt(i, 1) + " | " +
//                         model.getValueAt(i, 2) + " | " +
//                         model.getValueAt(i, 3) + " | " +
//                         model.getValueAt(i, 4) + "\n"
//                 );
//             }

//             fw.write("=================================\n\n");
//             fw.close();

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
