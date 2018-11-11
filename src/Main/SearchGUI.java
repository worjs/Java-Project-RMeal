package Main;

import static Data.RestaurantManager.*;
import static Main.CreateComponent.*;

import Data.*;
import Main.RMealMainGUI.mainGUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class SearchGUI extends JFrame {

  String[] answer = {"예", "아니오"};
  String[] searchResultNameList;
  JCheckBox[] typeCheckBox;
  JCheckBox[] costCheckBox;
  JCheckBox[] numOfPeopleCheckBox;
  JCheckBox[] locationCheckBox;
  SearchGUI searchGUI = this;
  JList<String> searchResultList;

  public static final int INITIAL_STATE = 0;
  public static final int SEARCHED_STATE = 1;

  SearchGUI(ArrayList<Restaurant> resList, OptionList optionList,
      ArrayList<Boolean> optionStateList,
      int state) {
    setTitle("RMeal");

    Container container = this.getContentPane();
    container.setBackground(Color.WHITE);
    container.setLayout(null);

    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {

        int choice = JOptionPane.showOptionDialog(container, "RMeal을 종료하시겠습니까?", "RMeal 종료",
            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, answer[0]);

        if (choice == 0) {
          System.exit(1);
        } else {
          return;
        }
      }
    });

    ///////////////////////////////////////////제목//////////////////////////////////////////

    JLabel titleLabel = createJLabel("식당 조회", 20, 20, 200, 50, 50);
    titleLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 40));
    container.add(titleLabel);

    JLabel explainLabel = createJLabel("조건을 선택하고 검색 버튼을 누르세요", 220, 30, 500, 50, 20);
    container.add(explainLabel);

    ///////////////////////////////////////////제목//////////////////////////////////////////

    //////////////////////////////////////////조건입력////////////////////////////////////////

    JPanel optionSetPanel = createJPanel(10, 80, 560, 600);
    optionSetPanel.setBackground(Color.WHITE);
    optionSetPanel.setLayout(new GridLayout(2, 2, 5, 5));
    container.add(optionSetPanel);

    JPanel typeCheckBoxPanel = new JPanel();
    typeCheckBoxPanel.setBackground(Color.WHITE);
    typeCheckBoxPanel.setLayout(new GridLayout(0, 1));
    typeCheckBoxPanel.setBorder(createTextBorder("식사", 28));
    optionSetPanel.add(typeCheckBoxPanel);

    JPanel costCheckBoxPanel = new JPanel();
    costCheckBoxPanel.setBackground(Color.WHITE);
    costCheckBoxPanel.setLayout(new GridLayout(0, 1));
    costCheckBoxPanel.setBorder(createTextBorder("가격대", 28));
    optionSetPanel.add(costCheckBoxPanel);

    JPanel numOfPeopleCheckBoxPanel = new JPanel();
    numOfPeopleCheckBoxPanel.setBackground(Color.WHITE);
    numOfPeopleCheckBoxPanel.setLayout(new GridLayout(0, 1));
    numOfPeopleCheckBoxPanel.setBorder(createTextBorder("인원", 28));
    optionSetPanel.add(numOfPeopleCheckBoxPanel);

    JPanel locationCheckBoxPanel = new JPanel();
    locationCheckBoxPanel.setBackground(Color.WHITE);
    locationCheckBoxPanel.setSize(400, 1000);
    locationCheckBoxPanel.setLayout(new GridLayout(0, 1));

    JScrollPane locationCheckBoxScrollPane = new JScrollPane(locationCheckBoxPanel,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    locationCheckBoxScrollPane.setBorder(createTextBorder("위치", 28));
    locationCheckBoxScrollPane.setBackground(Color.WHITE);
    optionSetPanel.add(locationCheckBoxScrollPane);
    if (state == INITIAL_STATE) {
      typeCheckBox = new JCheckBox[optionList.getList(optionList.TYPE).size()];
      for (int i = 0; i < optionList.getList(optionList.TYPE).size(); i++) {
        typeCheckBox[i] = createJCheckBox(optionList.getList(optionList.TYPE).get(i), 200, 50, 20);
        typeCheckBoxPanel.add(typeCheckBox[i]);
      }

      costCheckBox = new JCheckBox[optionList.getList(optionList.COST).size()];
      for (int i = 0; i < optionList.getList(optionList.COST).size(); i++) {
        costCheckBox[i] = createJCheckBox(optionList.getList(optionList.COST).get(i), 200, 50, 20);
        costCheckBoxPanel.add(costCheckBox[i]);
      }

      numOfPeopleCheckBox = new JCheckBox[optionList.getList(optionList.NUM).size()];
      for (int i = 0; i < optionList.getList(optionList.NUM).size(); i++) {
        numOfPeopleCheckBox[i] = createJCheckBox(optionList.getList(optionList.NUM).get(i), 200, 50,
            20);
        numOfPeopleCheckBoxPanel.add(numOfPeopleCheckBox[i]);
      }

      locationCheckBox = new JCheckBox[optionList.getList(optionList.LOC).size()];
      for (int i = 0; i < optionList.getList(optionList.LOC).size(); i++) {
        locationCheckBox[i] = createJCheckBox(optionList.getList(optionList.LOC).get(i), 200, 50,
            20);
        locationCheckBoxPanel.add(locationCheckBox[i]);
      }
    }

    if (state == SEARCHED_STATE) {
      typeCheckBox = new JCheckBox[optionList.getList(optionList.TYPE).size()];
      for (int i = 0; i < optionList.getList(optionList.TYPE).size(); i++) {
        typeCheckBox[i] = createJCheckBox(optionList.getList(optionList.TYPE).get(i), 200, 50, 20
            , optionStateList.get(i));
        typeCheckBoxPanel.add(typeCheckBox[i]);
      }

      costCheckBox = new JCheckBox[optionList.getList(optionList.COST).size()];
      for (int i = 0; i < optionList.getList(optionList.COST).size(); i++) {
        costCheckBox[i] = createJCheckBox(optionList.getList(optionList.COST).get(i), 200, 50, 20
            , optionStateList.get(i + 5));
        costCheckBoxPanel.add(costCheckBox[i]);
      }

      numOfPeopleCheckBox = new JCheckBox[optionList.getList(optionList.NUM).size()];
      for (int i = 0; i < optionList.getList(optionList.NUM).size(); i++) {
        numOfPeopleCheckBox[i] = createJCheckBox(optionList.getList(optionList.NUM).get(i), 200, 50,
            20, optionStateList.get(i + 10));
        numOfPeopleCheckBoxPanel.add(numOfPeopleCheckBox[i]);
      }

      locationCheckBox = new JCheckBox[optionList.getList(optionList.LOC).size()];
      for (int i = 0; i < optionList.getList(optionList.LOC).size(); i++) {
        locationCheckBox[i] = createJCheckBox(optionList.getList(optionList.LOC).get(i), 200, 50,
            20, optionStateList.get(i + 15));
        locationCheckBoxPanel.add(locationCheckBox[i]);
      }
    }

    //////////////////////////////////////////조건입력////////////////////////////////////////

    ////////////////////////////////////////검색결과 리스트/////////////////////////////////////

    String[] resNameList = new String[resList.size()];
    for (int i = 0; i < resList.size(); i++) {
      resNameList[i] = resList.get(i).getName();
    }

    if (state == INITIAL_STATE) {
      searchResultNameList = resNameList;

    }
    if (state == SEARCHED_STATE) {
      searchResultNameList = searchRestaurant(resList, optionList, optionStateList);
    }

    searchResultList = new JList<>(searchResultNameList);//인자로 들어갈 String 배열 다시 생각하기.
    searchResultList.setBorder(null);
    searchResultList.setFont(new Font("나눔스퀘어 Bold", Font.BOLD, 18));
    searchResultList.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        JList jlist = (JList) e.getSource();
        if (e.getClickCount() == 2) {
          Rectangle rectangle = jlist.getCellBounds(0, jlist.getLastVisibleIndex());
          if (rectangle != null && rectangle.contains(e.getPoint())) {
            int index = jlist.locationToIndex(e.getPoint());
            int i = 0;
            while (true) {
              if (searchResultNameList[index].equals(resList.get(i).getName())) {
                new RestaurantInfoGUI(resList.get(i), resList, searchGUI, optionList)
                    .setLocationRelativeTo(null);
                break;
              }
              i++;
            }

          }
        }
      }
    });

    JPanel searchResultListPanel = createJPanel(580, 80, 400, 600);
    searchResultListPanel.setFont(new Font("나눔스퀘어 Bold", Font.BOLD, 18));
    searchResultListPanel.setBorder(createTextBorder("검색 결과", 28));
    searchResultListPanel.setLayout(new GridLayout(0, 1));
    searchResultListPanel.setBackground(Color.WHITE);
    container.add(searchResultListPanel);

    JScrollPane searchResultListScrollPane = new JScrollPane(searchResultList,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    searchResultListScrollPane.setBorder(null);
    searchResultListPanel.add(searchResultListScrollPane);

    ////////////////////////////////////////검색결과 리스트/////////////////////////////////////

    ///////////////////////////////////////////버튼///////////////////////////////////////////

    JButton searchButton = createJButton("검색하기", 465, 695, 100, 50, 17);
    searchButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ArrayList<Boolean> optionStateList = optionStateList();
        dispose();
        new SearchGUI(resList, optionList, optionStateList, SearchGUI.SEARCHED_STATE)
            .setLocationRelativeTo(null);
      }
    });
    container.add(searchButton);

    JButton backButton = createJButton("돌아가기", 765, 696, 100, 50, 17);
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showOptionDialog(container, "메인 화면으로 돌아가시겠습니까?", "돌아가기",
            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, answer[0]);
        if (choice == 0) {
          dispose();
          new mainGUI(resList, optionList).setLocationRelativeTo(null);
        }
      }
    });
    container.add(backButton);

    JButton showButton = createJButton("정보보기", 875, 695, 100, 50, 17);
    showButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int i = 0;
        while (true) {
          if (searchResultList.getSelectedValue().equals(resList.get(i).getName())) {
            new RestaurantInfoGUI(resList.get(i), resList, searchGUI, optionList)
                .setLocationRelativeTo(null);
            break;
          }
          i++;
        }
      }
    });
    container.add(showButton);

    ///////////////////////////////////////////버튼///////////////////////////////////////////

    setSize(1000, 800);
    setVisible(true);

  }

  public ArrayList<Boolean> optionStateList() {

    ArrayList<Boolean> optionStateList = new ArrayList<Boolean>();

    for (int i = 0; i < typeCheckBox.length; i++) {
      optionStateList.add(this.typeCheckBox[i].isSelected());
    }

    for (int i = 0; i < costCheckBox.length; i++) {
      optionStateList.add(this.costCheckBox[i].isSelected());
    }

    for (int i = 0; i < numOfPeopleCheckBox.length; i++) {
      optionStateList.add(this.numOfPeopleCheckBox[i].isSelected());
    }

    for (int i = 0; i < locationCheckBox.length; i++) {
      optionStateList.add(this.locationCheckBox[i].isSelected());
    }

    return optionStateList;

  }
}
