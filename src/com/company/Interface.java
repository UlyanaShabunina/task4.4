package com.company;

import  javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface extends JFrame {
    // Модель данных таблицы
    private DefaultTableModel tableModel;
    private JTable table1;
    private int number = 1;
    private Timer timer1;
    private int health=0;
    private JPanel lol;
    private ArrayList <SortState> ListSort;
    private JLabel kek1;
    private JLabel kek2;
    private JProgressBar dada;
    private Boolean stop=false;
    private Boolean flag=true;
    private Boolean flag2=false;
    private Boolean flag3=true;
    private Boolean flag5=false;
    private int tmp;

    // Данные для таблиц
    // Заголовки столбцов
    private void removeColumn(int index, JTable myTable) {
        System.out.println(index);
        int nRow = myTable.getRowCount();
        int nCol = myTable.getColumnCount() - 1;
        Object[][] cells = new Object[nRow][nCol];
        String[] names = new String[nCol];

        for (int j = 0; j < nCol; j++) {
            if (j < index) {
                names[j] = myTable.getColumnName(j);
                for (int i = 0; i < nRow; i++) {
                    cells[i][j] = myTable.getValueAt(i, j);
                }
            } else {
                names[j] = myTable.getColumnName(j);
                for (int i = 0; i < nRow; i++) {
                    cells[i][j] = myTable.getValueAt(i, j + 1);
                }
            }
        }

        tableModel = new DefaultTableModel(cells, names);
        myTable.setModel(tableModel);
        number--;
    }

    public Interface(String[] args) {
        super("Пример использования TableModel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание стандартной модели
        tableModel = new DefaultTableModel();
        // Определение столбцов
        // Наполнение модели данными
        // Создание таблицы на основании модели данных
        table1 = new JTable(tableModel);
        table1.setRowHeight(210);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.setColumnSelectionAllowed(true);


        // Создание кнопки добавления строки таблицы
        JButton TakeFromFile = new JButton("Из txt");
        TakeFromFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer[][] dv;
                    JFileChooser jfc = new JFileChooser(".");
                    jfc.setDialogType(JFileChooser.OPEN_DIALOG);
                    if (jfc.showOpenDialog(Interface.this) != JFileChooser.APPROVE_OPTION)
                        return;
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    File file = new File(path);
                    Scanner scanner = null;
                    scanner = new Scanner(file);
                    String s;
                    ArrayList<Integer[]> kek = new ArrayList<>();
                    ArrayList<Integer> ama1 = new ArrayList<>();
                    while (scanner.hasNextLine()) {
                        s = scanner.nextLine();
                        String lol[] = s.split(" ");
                        for (int i = 0; i < lol.length; i++) {
                            ama1.add(Integer.parseInt(lol[i]));
                        }
                        Integer[] array1 = new Integer[ama1.size()];
                        ama1.toArray(array1);
                        kek.add(array1);
                        ama1.clear();
                    }
                    dv = new Integer[kek.size()][kek.get(0).length];
                    for (int i = 0; i < kek.size(); i++) {
                        for (int j = 0; j < kek.get(0).length; j++) {
                            dv[i][j] = kek.get(i)[j];
                        }

                    }
                    for (int g = 0; g < dv.length; g++) {
                        if (table1.getRowCount() == 0) {
                            Integer lol2[] = new Integer[table1.getRowCount()];
                            for (int i = 0; i < lol2.length; i++) {
                                lol2[i] = 0;
                            }
                            tableModel.addColumn("#" + number, lol2);
                            number++;
                        }
// Вставка новой строки после выделенной
                        String[] lol = new String[table1.getRowCount() + 1];
                        for (int i = 0; i < lol.length; i++) {
                            lol[i] = "0";
                        }
                        tableModel.addRow(lol);
                    }
                    for (int k = 0; k < dv[0].length - 1; k++) {
                        Integer lol2[] = new Integer[table1.getRowCount()];
                        for (int i = 0; i < lol2.length; i++) {
                            lol2[i] = 0;
                        }
                        tableModel.addColumn("#" + number, lol2);
                        number++;
                    }
                    for (int g = 0; g < dv.length; g++) {
                        for (int f = 0; f < dv[0].length; f++) {
                            tableModel.setValueAt(dv[g][f], g, f);
                        }
                    }
                } catch (Exception v) {
                    System.out.println("Введите нормальное имя");
                }
            }
        });
        JButton AddColumn = new JButton("Добавить ячейку");
        AddColumn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                if (table1.getRowCount() == 0) {
                    Integer lol3[] = new Integer[table1.getRowCount()];
                    for (int i = 0; i < lol3.length; i++) {
                        lol3[i] = 0;
                    }
                    tableModel.addRow(lol3);
                }
                int idx = table1.getSelectedRow();
                Integer lol2[] = new Integer[table1.getRowCount()];
                for (int i = 0; i < lol2.length; i++) {
                    lol2[i] = 0;
                }
                tableModel.addColumn("#" + Integer.toString(number));
                number++;
            }
        });
        JButton Stop = new JButton("Остановка");
        Stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             stop=true;
             flag3=true;
             tmp=health;
             flag5=true;
            }
        });
        JButton Backward = new JButton("Назад");
        Backward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                health=tmp;
                if (health == 0) {
                    JOptionPane.showMessageDialog(null, "Массив в начальном положении");
                }
                if (health > 0) {
                    if (flag) {
                        health -= 2;
                    } else {
                        health--;
                    }
                    int[] dv3 = new int[ListSort.get(health).getStage().length];
                    for (int i = 0; i < dv3.length; i++) {
                        dv3[i] = ListSort.get(health).getStage()[i];
                    }
                    for (int g = 0; g < 1; g++) {
                        for (int f = 0; f < dv3.length; f++) {
                            tableModel.setValueAt(dv3[f], g, f);
                        }
                    }
                    kek1.setText("Переменная i = " + ListSort.get(health).getI());
                    kek2.setText("Переменная k =" + ListSort.get(health).getK());
                    dada.setValue((int) (((double) health / (ListSort.size())) * 100));
                } else {
                    kek1.setText("Переменная i = " + 0);
                    kek2.setText("Переменная k = " + 0);
                    dada.setValue(0);
                }
                flag=false;
                flag2=true;
                tmp=health;
            }
        });
        JButton Forward = new JButton("Вперед");
        Forward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                health=tmp;
                if (!flag){
                    health++;
                }
                flag=true;
                if (health < ListSort.size()) {
                    int[] dv3 = new int[ListSort.get(health).getStage().length];
                    for (int i = 0; i < dv3.length; i++) {
                        dv3[i] = ListSort.get(health).getStage()[i];
                    }
                    for (int g = 0; g < 1; g++) {
                        for (int f = 0; f < dv3.length; f++) {
                            tableModel.setValueAt(dv3[f], g, f);
                        }
                    }
                    kek1.setText("Переменная i = " + ListSort.get(health).getI());
                    kek2.setText("Переменная k =" + ListSort.get(health).getK());
                    dada.setValue((int) (((double) health / (ListSort.size())) * 100));
                } else {
                    kek1.setText("Переменная i = " + 0);
                    kek2.setText("Переменная k = " + 0);
                    dada.setValue(100);
                }
                if (health!=ListSort.size()) {
                    health++;
                } else {
                    JOptionPane.showMessageDialog(null,"Массив отсортирован");
                }
                flag2=true;
                flag5=true;
                tmp=health;
            }
        });
        JButton Calculate = new JButton("Сортировка");
        Calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag3) {
                    flag3=false;
                    try {
                        int[][] dv2 = new int[table1.getRowCount()][table1.getColumnCount()];
                        for (int i = 0; i <= table1.getRowCount() - 1; i++) {
                            for (int j = 0; j <= table1.getColumnCount() - 1; j++) {
                                dv2[i][j] = Integer.parseInt(String.valueOf(table1.getValueAt(i, j)));
                            }
                        }
                        int[] dv1 = new int[dv2[0].length];
                        for (int i = 0; i < dv2[0].length; i++) {
                            dv1[i] = dv2[0][i];
                        }
                        ListSort = Sort.sort(dv1);
                        timer1 = new Timer(1000, new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (flag5){
                                    tmp=health;
                                    health=0;
                                    flag5=false;
                                }
                                if (flag2) {
                                    health = 0;
                                    flag2 = false;
                                }
                                if (!stop) {
                                    //код который нужно выполнить каждую секунду
                                    if (health < ListSort.size()) {
                                        int[] dv3 = new int[ListSort.get(health).getStage().length];
                                        for (int i = 0; i < dv3.length; i++) {
                                            dv3[i] = ListSort.get(health).getStage()[i];
                                        }
                                        for (int g = 0; g < 1; g++) {
                                            for (int f = 0; f < dv2[0].length; f++) {
                                                tableModel.setValueAt(dv3[f], g, f);
                                            }
                                        }
                                    } else {
                                        timer1.stop();
                                    }
                                    if (health < ListSort.size()) {
                                        kek1.setText("Переменная i = " + ListSort.get(health).getI());
                                        kek2.setText("Переменная k =" + ListSort.get(health).getK());
                                        dada.setValue((int) (((double) health / (ListSort.size())) * 100));
                                    } else {
                                        kek1.setText("Переменная i = " + 0);
                                        kek2.setText("Переменная k = " + 0);
                                        dada.setValue(100);
                                    }
                                    health++;
                                } else {
                                    timer1.stop();
                                }
                                if (dada.getValue()==100){
                                    stop=true;
                                    flag3=true;
                                    tmp=health;
                                    flag5=true;
                                }
                            }
                        });
                        timer1.start(); //запуск таймера
                        if (!stop) {
                            health = 0;
                        }
                        stop = false;
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, "Для сортировки введите массив из чисел!");
                    }
                }
            }
        });
        // Создание кнопки удаления строки таблицы
        JButton Remove = new JButton("Удалить");
        Remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                // Удаление выделенной строки
                try {
                    removeColumn(table1.getSelectedColumn(), table1);
                }catch (Exception exp){
                    JOptionPane.showMessageDialog(null, "Добавьте ячейку!");
                }
            }
        });
        JButton Record = new JButton("Запись в txt");
        Record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(".");
                jfc.setDialogType(JFileChooser.OPEN_DIALOG);
                if (jfc.showOpenDialog(Interface.this) != JFileChooser.APPROVE_OPTION)
                    return;
                String path = jfc.getSelectedFile().getAbsolutePath();
                path=path+".txt";
                File file2 = new File(path);
                try {
                    PrintStream pw = new PrintStream(file2);
                    int[][] dv2 = new int[table1.getRowCount()][table1.getColumnCount()];
                    for (int i = 0; i <= table1.getRowCount() - 1; i++) {
                        for (int j = 0; j <= table1.getColumnCount() - 1; j++) {
                            dv2[i][j] = Integer.parseInt(String.valueOf(table1.getValueAt(i, j)));
                        }
                    }
                    for (int g = 0; g < dv2.length; g++) {
                        for (int f = 0; f < dv2[0].length; f++) {
                            pw.print(dv2[g][f] + " ");
                        }
                        pw.println();
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        // Создание таблицы на основе модели данных
        // Определение высоты строки

        // Формирование интерфейса
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        getContentPane().add(contents);
        lol = new JPanel(new GridBagLayout());
        kek1 = new JLabel("Переменная i = "+0);
        lol.add(kek1, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL, new Insets(90,2,2,2),0,0));
        kek2 = new JLabel("Переменная k ="+0);
        lol.add(kek2, new GridBagConstraints(0,0,1,1,1,1, GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL, new Insets(110,2,0,2),0,0));
        JPanel buttons = new JPanel();
        buttons.add(AddColumn);
        buttons.add(TakeFromFile);
        buttons.add(Calculate);
        buttons.add(Remove);
        buttons.add(Record);
        buttons.add(Stop);
        buttons.add(Forward);
        buttons.add(Backward);
        dada = new JProgressBar();
        dada.setMinimum(0);
        dada.setMaximum(100);
        dada.setPreferredSize(new Dimension(100,15));
        buttons.add(dada);
        getContentPane().add(buttons, "South");
        getContentPane().add(lol, "East");
        // Вывод окна на экран
        setSize(900, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}