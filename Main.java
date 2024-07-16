import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Main extends JFrame { // This is the Main class that extends JFrame, indicating that
                                    // it is a GUI from Swing.

    JPanel panel, titlePanel, instructionPanel, questionsPanel; // To separate the screen into panel components
    JLabel descriptionLabel, questionLabel, warningLabel, timerLabel; // To provide descriptions and display information
    JButton startButton, nextButton, cancelButton; //Buttons declared for user interaction.
    JRadioButton[] answerChoices; // to implement the choices that will contain answers from that the user will select
    ButtonGroup answerGroup; // Grouping the answer choices

    char[] letterOption = {'A', 'B','C', 'D'}; // An array declared to store and display the different answer options

    ArrayList<Question> questions = new ArrayList<>(); //An Array List that will store questions created from the Question Class
    private int score = 0; //  To keep track of the score as the user plays the game
    private int currentQuestionIndex = 0; // A field that will keep track of the questions being displayed

    private Timer timer; //A field declared from the Timer class that enables the implementation of a countdown timer
    private int timeLeft = 180; // 3 minutes in seconds

    public Main() {
        setTitle("QUIZ YA GEN");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Creating a new instance of BoxLayout that
                                // accepts two arguments. The first argument specifies the container that the Layout will use
                            // and the second one specifies the axis along which the components will be laid out

        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel titleLabel1 = new JLabel("QUIZ ");
        JLabel titleLabel2 = new JLabel("    YA");
        JLabel titleLabel3 = new JLabel("      GEN");
        titleLabel1.setAlignmentX(Component.CENTER_ALIGNMENT); // Aligning the component along the center of the Y_Axis
        titleLabel1.setForeground(Color.GREEN);
        titleLabel1.setFont(new Font("Comic Sans MS", Font.BOLD, 43));
        titleLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel2.setForeground(Color.RED);
        titleLabel2.setFont(new Font("ARIAL", Font.BOLD, 43));
        titleLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel3.setForeground(Color.ORANGE);
        titleLabel3.setFont(new Font("Comic Sans MS", Font.BOLD, 43));

        //The descriptionLabel contains text enclosed in <html> tags in order to wrap the text and fit it according to window size.
        descriptionLabel = new JLabel("<html><center>A quiz of 100 random questions<br>on Zambia to test how well you<br>know our Country</center></html>");
        descriptionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton = new JButton("START");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(new Color(204, 255, 204));
        startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        startButton.setBorderPainted(false);
        startButton.setFocusable(false);

        titlePanel.add(titleLabel1);
        titlePanel.add(titleLabel2);
        titlePanel.add(titleLabel3);

        panel.add(Box.createVerticalGlue()); //Creates an invisible and expandable space between components that can grow and shrink accordingly
        panel.add(titlePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(descriptionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(startButton);
        panel.add(Box.createVerticalGlue());
        add(panel);

        setVisible(true);
        startButton.addActionListener(new StartButtonListener());

        initializeQuestionsPanel();
    }

    private void initializeQuestionsPanel() { // A method that sets up the questions that are going to be used in the program
        /*The following 100 statements simply add questions to the questions Array List that was created using the Question class
        The questions are created using a constructor that contains the question text, an array of possible answers and a field that defines the actual answer
        to the question.
        */
        questions.add(new Question("What is the capital city of Zambia?", new String[]{"Lusaka", "Ndola", "Kitwe", "Livingstone"}, 0));
        questions.add(new Question("When did Zambia gain independence?", new String[]{"1964", "1974", "1984", "1994"}, 0));
        questions.add(new Question("Who was the first President of Zambia?", new String[]{"Kenneth Kaunda", "Levy Mwanawasa", "Michael Sata", "Edgar Lungu"}, 0));
        questions.add(new Question("Which river forms the border between Zambia and Zimbabwe?", new String[]{"Zambezi", "Kafue", "Luangwa", "Chambeshi"}, 0));
        questions.add(new Question("What is the official language of Zambia?", new String[]{"English", "Bemba", "Nyanja", "Tonga"}, 0));
        questions.add(new Question("Which is the largest national park in Zambia?", new String[]{"Kafue National Park", "South Luangwa National Park", "Lower Zambezi National Park", "Liuwa Plain National Park"}, 0));
        questions.add(new Question("What currency is used in Zambia?", new String[]{"Zambian Kwacha", "US Dollar", "South African Rand", "Euro"}, 0));
        questions.add(new Question("Which waterfall is a major tourist attraction in Zambia?", new String[]{"Victoria Falls", "Kalambo Falls", "Ngonye Falls", "Lumangwe Falls"}, 0));
        questions.add(new Question("What is Zambia's main export?", new String[]{"Copper", "Gold", "Diamonds", "Coffee"}, 0));
        questions.add(new Question("What is the name of the traditional ceremony of the Lozi people?", new String[]{"Kuomboka", "Nc'wala", "Mutomboko", "Shimunenga"}, 0));
        questions.add(new Question("Which lake is shared by Zambia and the Democratic Republic of the Congo?", new String[]{"Lake Tanganyika", "Lake Bangweulu", "Lake Mweru", "Lake Kariba"}, 2));
        questions.add(new Question("What is the highest point in Zambia?", new String[]{"Mafinga Central", "Mount Kilimanjaro", "Nyika Plateau", "Zambezi Escarpment"}, 0));
        questions.add(new Question("Which city is known as the Copperbelt hub?", new String[]{"Kitwe", "Chingola", "Mufulira", "Ndola"}, 0));
        questions.add(new Question("What is the main religion in Zambia?", new String[]{"Christianity", "Islam", "Hinduism", "Buddhism"}, 0));
        questions.add(new Question("Which Zambian president was known as 'The Cobra'?", new String[]{"Michael Sata", "Kenneth Kaunda", "Frederick Chiluba", "Edgar Lungu"}, 0));
        questions.add(new Question("Which ethnic group is the largest in Zambia?", new String[]{"Bemba", "Tonga", "Lozi", "Chewa"}, 0));
        questions.add(new Question("What is the main ingredient in the traditional Zambian dish 'Nshima'?", new String[]{"Maize", "Rice", "Cassava", "Sorghum"}, 0));
        questions.add(new Question("Which Zambian town is famous for its emerald mines?", new String[]{"Kagem", "Solwezi", "Chililabombwe", "Kabwe"}, 0));
        questions.add(new Question("What is the main legislative body in Zambia?", new String[]{"National Assembly", "House of Commons", "Senate", "Parliament"}, 0));
        questions.add(new Question("Which Zambian river is the longest?", new String[]{"Zambezi", "Kafue", "Luangwa", "Chambeshi"}, 0));
        questions.add(new Question("Who was Zambia's second president?", new String[]{"Frederick Chiluba", "Kenneth Kaunda", "Levy Mwanawasa", "Michael Sata"}, 0));
        questions.add(new Question("What is the name of the traditional ceremony of the Ngoni people?", new String[]{"Nc'wala", "Kuomboka", "Mutomboko", "Shimunenga"}, 0));
        questions.add(new Question("Which Zambian city is known for its proximity to Victoria Falls?", new String[]{"Livingstone", "Lusaka", "Kitwe", "Ndola"}, 0));
        questions.add(new Question("What time is lunchtime in Zambia?", new String[]{"12:00", "14:00", "12:30", "11:50"}, 0));
        questions.add(new Question("What is the largest stadium in Zambia?", new String[]{"Heroes Stadium", "Nkana Stadium", "Arthur Davies Stadium", "Nchanga Stadium"}, 0));
        questions.add(new Question("What do the intials ZANACO stand for?", new String[]{"Zambia National Commercial Bank", "Zambia National Financial Bank", "National Savings Bank", "First National Bank"}, 0));
        questions.add(new Question("Who is the speaker of the National Assembly?", new String[]{"Patrick Matibini", "Ireen Mambilima", "Edgar Lungu", "First National Bank"}, 3));
        questions.add(new Question("Which Zambian city is known for its proximity to Victoria Falls?", new String[]{"Livingstone", "Lusaka", "Kitwe", "Ndola"}, 0));
        questions.add(new Question("What is the name of Zambia's central bank?", new String[]{"Bank of Zambia", "Reserve Bank of Zambia", "Central Bank of Zambia", "National Bank of Zambia"}, 0));
        questions.add(new Question("What is the predominant climate in Zambia?", new String[]{"Tropical", "Desert", "Mediterranean", "Polar"}, 0));
        questions.add(new Question("Which Zambian musician is known for the song 'Dununa Reverse'?", new String[]{"David Phiri", "Macky 2", "B Flow", "Slap Dee"}, 1));
        questions.add(new Question("What is the population of Zambia approximately?", new String[]{"18 million", "10 million", "25 million", "5 million"}, 0));
        questions.add(new Question("Which Zambian province is the largest by area?", new String[]{"Western", "Eastern", "Northern", "Southern"}, 0));
        questions.add(new Question("Which traditional ceremony is celebrated by the Lunda people?", new String[]{"Mutomboko", "Kuomboka", "Nc'wala", "Shimunenga"}, 0));
        questions.add(new Question("Which Zambian town is known for its rich wildlife and safari lodges?", new String[]{"South Luangwa", "Kafue", "Livingstone", "Kasama"}, 0));
        questions.add(new Question("What is the primary language spoken by the Bemba people?", new String[]{"Bemba", "Nyanja", "Lozi", "Tonga"}, 0));
        questions.add(new Question("Who was the president of Zambia before Edgar Lungu?", new String[]{"Michael Sata", "Levy Mwanawasa", "Rupiah Banda", "Frederick Chiluba"}, 2));
        questions.add(new Question("What is the national bird of Zambia?", new String[]{"African Fish Eagle", "Peacock", "Flamingo", "Ostrich"}, 0));
        questions.add(new Question("Which Zambian province is famous for its hot springs?", new String[]{"Eastern", "Northern", "Southern", "Western"}, 1));
        questions.add(new Question("What is the main ingredient in the traditional Zambian dish 'Chikanda'?", new String[]{"Ground Orchid Tubers", "Maize", "Cassava", "Millet"}, 0));
        questions.add(new Question("Which Zambian city is known for its coal mines?", new String[]{"Maamba", "Solwezi", "Lusaka", "Chililabombwe"}, 0));
        questions.add(new Question("What is the main industry in the Copperbelt region?", new String[]{"Mining", "Agriculture", "Tourism", "Fishing"}, 0));
        questions.add(new Question("Which Zambian river is known for its white-water rafting opportunities?", new String[]{"Zambezi River", "Kafue River", "Luangwa River", "Chambeshi River"}, 0));
        questions.add(new Question("What is the main festival celebrated by the Tonga people?", new String[]{"Lwiindi", "Kuomboka", "Nc'wala", "Mutomboko"}, 0));
        questions.add(new Question("Which Zambian national park is known for its walking safaris?", new String[]{"South Luangwa National Park", "Kafue National Park", "Lower Zambezi National Park", "Liuwa Plain National Park"}, 0));
        questions.add(new Question("What is the traditional name for the Victoria Falls in Zambia?", new String[]{"Mosi-oa-Tunya", "Shungunamutitima", "Livingstone Falls", "Ngonye Falls"}, 0));
        questions.add(new Question("Which traditional instrument is commonly used in Zambian music?", new String[]{"Mbira", "Marimba", "Kalimba", "Ngoma drum"}, 3));
        questions.add(new Question("Which Zambian province is home to the Lower Zambezi National Park?", new String[]{"Southern Province", "Central Province", "Eastern Province", "Western Province"}, 2));
        questions.add(new Question("Who is considered the father of Zambian independence?", new String[]{"Kenneth Kaunda", "Simon Kapwepwe", "Harry Nkumbula", "Frederick Chiluba"}, 0));
        questions.add(new Question("Which Zambian traditional ceremony involves the crossing of a flooded plain?", new String[]{"Kuomboka", "Nc'wala", "Lwiindi", "Mutomboko"}, 0));
        questions.add(new Question("What is the primary source of power generation in Zambia?", new String[]{"Hydroelectric", "Thermal", "Solar", "Nuclear"}, 0));
        questions.add(new Question("Which Zambian province is known for its production of honey?", new String[]{"North-Western Province", "Eastern Province", "Western Province", "Lusaka Province"}, 0));
        questions.add(new Question("What is the main ingredient in the traditional Zambian dish 'Chikanda'?", new String[]{"Ground Orchid Tubers", "Maize", "Cassava", "Millet"}, 0));
        questions.add(new Question("Which Zambian city is known for its coal mines?", new String[]{"Maamba", "Solwezi", "Lusaka", "Chililabombwe"}, 0));
        questions.add(new Question("What is the main industry in the Copperbelt region?", new String[]{"Mining", "Agriculture", "Tourism", "Fishing"}, 0));
        questions.add(new Question("Which Zambian river is known for its white-water rafting opportunities?", new String[]{"Zambezi River", "Kafue River", "Luangwa River", "Chambeshi River"}, 0));
        questions.add(new Question("What is the main festival celebrated by the Tonga people?", new String[]{"Lwiindi", "Kuomboka", "Nc'wala", "Mutomboko"}, 0));
        questions.add(new Question("Which Zambian national park is known for its walking safaris?", new String[]{"South Luangwa National Park", "Kafue National Park", "Lower Zambezi National Park", "Liuwa Plain National Park"}, 0));
        questions.add(new Question("What is the traditional name for the Victoria Falls in Zambia?", new String[]{"Mosi-oa-Tunya", "Shungunamutitima", "Livingstone Falls", "Ngonye Falls"}, 0));
        questions.add(new Question("Which traditional instrument is commonly used in Zambian music?", new String[]{"Mbira", "Marimba", "Kalimba", "Ngoma drum"}, 3));
        questions.add(new Question("What is Zambia's national sport?", new String[]{"Football (Soccer)", "Cricket", "Rugby", "Basketball"}, 0));
        questions.add(new Question("Which province is known as the 'Breadbasket of Zambia'?", new String[]{"Central Province", "Eastern Province", "Southern Province", "Northern Province"}, 0));
        questions.add(new Question("What is the main ingredient in the Zambian dish 'Ifisashi'?", new String[]{"Groundnuts", "Fish", "Beef", "Chicken"}, 0));
        questions.add(new Question("Which Zambian province is Lusaka located in?", new String[]{"Lusaka Province", "Central Province", "Eastern Province", "Western Province"}, 0));
        questions.add(new Question("Which Zambian river is home to the Barotse Floodplain?", new String[]{"Zambezi River", "Kafue River", "Luangwa River", "Chambeshi River"}, 0));
        questions.add(new Question("Which Zambian traditional dance is performed during the Kuomboka ceremony?", new String[]{"Makishi", "Gule Wamkulu", "Vimbuza", "Muchongoyo"}, 0));
        questions.add(new Question("What is the main ingredient in the traditional Zambian drink 'Munkoyo'?", new String[]{"Maize", "Millet", "Cassava", "Sorghum"}, 0));
        questions.add(new Question("Which Zambian city is known for its emerald mines?", new String[]{"Ndola", "Kitwe", "Lusaka", "Solwezi"}, 3));
        questions.add(new Question("Which Zambian river flows into the Indian Ocean?", new String[]{"Zambezi River", "Kafue River", "Luangwa River", "Chambeshi River"}, 0));
        questions.add(new Question("What is the name of the traditional Zambian musical instrument similar to a xylophone?", new String[]{"Silimba", "Ngoma", "Kalimba", "Mbira"}, 0));
        questions.add(new Question("Which Zambian city is known for its copper mines?", new String[]{"Kitwe", "Lusaka", "Livingstone", "Kasama"}, 0));
        questions.add(new Question("What is the main ingredient in the Zambian dish 'Nshima'?", new String[]{"Maize", "Rice", "Wheat", "Cassava"}, 0));
        questions.add(new Question("Who was the first President of Zambia?", new String[]{"Kenneth Kaunda", "Levy Mwanawasa", "Michael Sata", "Edgar Lungu"}, 0));
        questions.add(new Question("Which river forms the border between Zambia and Zimbabwe?", new String[]{"Zambezi", "Kafue", "Luangwa", "Chambeshi"}, 0));
        questions.add(new Question("What is the official language of Zambia?", new String[]{"English", "Bemba", "Nyanja", "Tonga"}, 0));
        questions.add(new Question("Which is the largest national park in Zambia?", new String[]{"Kafue National Park", "South Luangwa National Park", "Lower Zambezi National Park", "Liuwa Plain National Park"}, 0));
        questions.add(new Question("What currency is used in Zambia?", new String[]{"Zambian Kwacha", "US Dollar", "South African Rand", "Euro"}, 0));
        questions.add(new Question("Which waterfall is a major tourist attraction in Zambia?", new String[]{"Victoria Falls", "Kalambo Falls", "Ngonye Falls", "Lumangwe Falls"}, 0));
        questions.add(new Question("What is Zambia's main export?", new String[]{"Copper", "Gold", "Diamonds", "Coffee"}, 0));
        questions.add(new Question("What is the name of the traditional ceremony of the Lozi people?", new String[]{"Kuomboka", "Nc'wala", "Mutomboko", "Shimunenga"}, 0));
        questions.add(new Question("Which lake is shared by Zambia and the Democratic Republic of the Congo?", new String[]{"Lake Tanganyika", "Lake Bangweulu", "Lake Mweru", "Lake Kariba"}, 2));
        questions.add(new Question("What is the highest point in Zambia?", new String[]{"Mafinga Central", "Mount Kilimanjaro", "Nyika Plateau", "Zambezi Escarpment"}, 0));
        questions.add(new Question("Which city is known as the Copperbelt hub?", new String[]{"Kitwe", "Chingola", "Mufulira", "Ndola"}, 0));
        questions.add(new Question("What is the main religion in Zambia?", new String[]{"Christianity", "Islam", "Hinduism", "Buddhism"}, 0));
        questions.add(new Question("Which Zambian president was known as 'The Cobra'?", new String[]{"Michael Sata", "Kenneth Kaunda", "Frederick Chiluba", "Edgar Lungu"}, 0));
        questions.add(new Question("Which Zambian city is known for its proximity to Victoria Falls?", new String[]{"Livingstone", "Lusaka", "Kitwe", "Ndola"}, 0));
        questions.add(new Question("What is the name of Zambia's central bank?", new String[]{"Bank of Zambia", "Reserve Bank of Zambia", "Central Bank of Zambia", "National Bank of Zambia"}, 0));
        questions.add(new Question("What is the predominant climate in Zambia?", new String[]{"Tropical", "Desert", "Mediterranean", "Polar"}, 0));
        questions.add(new Question("Which Zambian musician is known for the song 'Dununa Reverse'?", new String[]{"David Phiri", "Macky 2", "B Flow", "Slap Dee"}, 1));
        questions.add(new Question("What is the population of Zambia approximately?", new String[]{"18 million", "10 million", "25 million", "5 million"}, 0));
        questions.add(new Question("Which Zambian province is the largest by area?", new String[]{"Western", "Eastern", "Northern", "Southern"}, 0));
        questions.add(new Question("Which traditional ceremony is celebrated by the Lunda people?", new String[]{"Mutomboko", "Kuomboka", "Nc'wala", "Shimunenga"}, 0));
        questions.add(new Question("Which Zambian town is known for its rich wildlife and safari lodges?", new String[]{"South Luangwa", "Kafue", "Livingstone", "Kasama"}, 0));
        questions.add(new Question("What is the primary language spoken by the Bemba people?", new String[]{"Bemba", "Nyanja", "Lozi", "Tonga"}, 0));
        questions.add(new Question("Who was the president of Zambia before Edgar Lungu?", new String[]{"Michael Sata", "Levy Mwanawasa", "Rupiah Banda", "Frederick Chiluba"}, 2));
        questions.add(new Question("What is the national animal of Zambia?", new String[]{"Lion", "Leopard", "Elephant", "Giraffe"}, 0));
        questions.add(new Question("Which river is the longest in Zambia?", new String[]{"Zambezi", "Kafue", "Luangwa", "Chambeshi"}, 0));
        questions.add(new Question("What is the highest point in Zambia?", new String[]{"Mafinga Central", "Mount Kilimanjaro", "Nyika Plateau", "Zambezi Escarpment"}, 0));
        questions.add(new Question("Which city is known as the Copperbelt hub?", new String[]{"Kitwe", "Chingola", "Mufulira", "Ndola"}, 0));
        questions.add(new Question("What is the main religion in Zambia?", new String[]{"Christianity", "Islam", "Hinduism", "Buddhism"}, 0));
        questions.add(new Question("Which Zambian president was known as 'The Cobra'?", new String[]{"Michael Sata", "Kenneth Kaunda", "Frederick Chiluba", "Edgar Lungu"}, 0));

        //Define a label for the timer
        timerLabel = new JLabel("Time Left: 3:00");
        timerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        timerLabel.setForeground(Color.RED);
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Define a panel that will contain questions
        questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
        questionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Creating an empty border to add padding

        JPanel questionPanel = new JPanel();
        questionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        questionPanel.add(questionLabel);
        questionPanel.add(timerLabel);

        JPanel answerPanel = new JPanel();
        answerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));

        answerChoices = new JRadioButton[4];
        answerGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            answerChoices[i] = new JRadioButton(); //Creates a JRadioButton instance and assigns it to the i'th posstion of the answer choices array
                                        // since answer choices is an array created using JRadioButton
            answerChoices[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            answerGroup.add(answerChoices[i]); //Adds the i'th JRadioButton to a Button Group that bundles the answers together
            answerPanel.add(answerChoices[i]);//Adds the i'th JRadioButton to the answer panel so that it may be rendered on the GUI window
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Define the outlook of the Next Button that allows users to proceed to the next question
        nextButton = new JButton("Next");
        nextButton.setBackground(Color.GREEN);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        nextButton.setBorderPainted(false);
        nextButton.setFocusable(false);

        //Define the outlook of the the Cancel Button that allows user to cancel the Quiz
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusable(false);

        //Defines a label used to warn the users if they Click the Next Button without selecting an answer
        warningLabel = new JLabel();
        warningLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        warningLabel.setForeground(Color.RED);

        //Adds the two buttons and warning label to a Panel that groups the three components together
        buttonPanel.add(nextButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(warningLabel);

        questionsPanel.add(questionPanel);
        questionsPanel.add(answerPanel);
        questionsPanel.add(buttonPanel);

        //Defines Action listeners that respond and perform functionality when the Next and Cancel Buttons are clicked
        nextButton.addActionListener(new NextButtonActionListener());
        cancelButton.addActionListener(new CancelButtonListener());
    }

    //A method that defines the instructions page that gives users rules for playing the Quiz
    private void showInstructionsPage() {
        instructionPanel = new JPanel();
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
        instructionPanel.setBackground(new Color(230, 255, 230));

        JLabel instructionTitle = new JLabel("Instructions");
        instructionTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        instructionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionTitle.setForeground(Color.ORANGE);

        JLabel instructions = new JLabel("<html><center>The time limit for the quiz is 30 minutes. You will be asked multiple choice questions based on Zambia and once you make your selection, you cannot alter the answer.</center></html>");
        instructions.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setHorizontalAlignment(JLabel.CENTER);

        JButton playButton = new JButton("PLAY");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setBackground(Color.GREEN);
        playButton.setForeground(Color.WHITE);
        playButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        playButton.setBorderPainted(false);
        playButton.setFocusable(false);

        playButton.addActionListener(new PlayButtonListener());

        instructionPanel.add(Box.createVerticalGlue());
        instructionPanel.add(instructionTitle);
        instructionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        instructionPanel.add(instructions);
        instructionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        instructionPanel.add(playButton);
        instructionPanel.add(Box.createVerticalGlue());
        getContentPane().removeAll(); // Clears content of the previous (Start Page/Screen) when the Instruction page renders
        add(instructionPanel);
        revalidate();
        repaint();
    }

    //Method that displays the questions
    private void showQuestionsPage() {
        //if the panel that holds questions is blank, the intializeQuestionsPanel method is called so that the questions are prepared
        if (questionsPanel == null) {
            initializeQuestionsPanel();
        }

        getContentPane().removeAll(); // Clears the previous content
        add(questionsPanel);
        revalidate();
        repaint();
        displayCurrentQuestion(); // Calls a method that displays the current questio to be answered
        timeLeft = 180;
        updateTimerLabel();
        startTimer();
    }

    //Method that selects a random question from the Array List and displays it on the screen
    private void displayCurrentQuestion() {

        int questionToDisplay = (int) (Math.random() * questions.size()); // generate and store a random index that will be
                                                        // used to choose a question

        Question currentQuestion = questions.get(questionToDisplay); // Retrieve a random question from the Array List

        //Display the randomly selected question and display it on the screen
        questionLabel.setText("<html>" + (currentQuestionIndex + 1) + ". "+ currentQuestion.getQuestionText() + "</html>");

        //Displays the options or possible answers on the screen from which the user will be allowed to choose
        String[] options = currentQuestion.getOptions();
        for (int i = 0; i < options.length; i++) {
            answerChoices[i].setText(letterOption[i] + ". " + options[i]);
            answerChoices[i].setSelected(false);
        }

        //Sets the warning label text to blank if the user has selected/ chosen an answer
        warningLabel.setText("");
    }

    // An action lister that calls upon a method that displays the instructions page when the start button is clicked on the Start Page
    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showInstructionsPage();
        }
    }

    // An Action lister that calls upon a method that displays questions on the screen when the Play button on the Instructions page is clicked
    private class PlayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showQuestionsPage();
        }
    }

    //An action lister that renders the next question to be answered or results page  when the user clicks the 'Next' Button after choosing an answer for a question
    private class NextButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int chosenAnswer = -1; //Declare a field to store the chosen answer

            for (int j = 0; j < answerChoices.length; j++) {
                if (answerChoices[j].isSelected()) { // If a radio button is selected, the 'chooseAnswer' field is assigned with the index of the answer that was selected
                    chosenAnswer = j;
                    break;
                }
            }

            if (chosenAnswer == -1) { // Will display a warning because No answer was chosen
                warningLabel.setText("Please select an option.");
                return;
            }

            // Updates the score field by 1 if the answer selected matches with the actual that the question holds.
            Question currentQuestion = questions.get(currentQuestionIndex);
            if (currentQuestion.isCorrect(chosenAnswer)) {
                score++;
            }

            currentQuestionIndex++; //Increments the index of the questions

            //If the index field's value is less than the size of the array, another question will be rendered
            if (currentQuestionIndex < questions.size()) {
                answerGroup.clearSelection();
                displayCurrentQuestion();

            } else {
                //if it is not, the timer stops and redirects to the game Over screen that displays the score
                timer.stop();
                showGameOverScreen();
            }
        }
    }

    // An Action Listener that cancels the game when clicked. It redirects to the game over screen
    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            showGameOverScreen();
        }
    }

    // Method that displays the results, and time (if any left) when the user finishes playing or cancels the game
    private void showGameOverScreen() {
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
        gameOverPanel.setBackground(new Color(230, 255, 230));

        JLabel titleLabel = new JLabel("QUIZ YA GEN");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        gameOverLabel.setForeground(Color.ORANGE);
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel("Your Score: " + score + "/" + questions.size());
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel timeLabel = new JLabel(String.format("Time Left: %02d:%02d", timeLeft / 60, timeLeft % 60));
        timeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        timeLabel.setForeground(Color.RED);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playAgainButton = new JButton("PLAY AGAIN");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.setBackground(Color.GREEN);
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        playAgainButton.setBorderPainted(false);
        playAgainButton.setFocusable(false);

        JButton endGameButton = new JButton("END GAME");
        endGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        endGameButton.setBackground(Color.RED);
        endGameButton.setForeground(Color.WHITE);
        endGameButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        endGameButton.setBorderPainted(false);
        endGameButton.setFocusable(false);

        gameOverPanel.add(Box.createVerticalGlue());
        gameOverPanel.add(titleLabel);
        gameOverPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        gameOverPanel.add(gameOverLabel);
        gameOverPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        gameOverPanel.add(scoreLabel);
        gameOverPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        gameOverPanel.add(timeLabel);
        gameOverPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        gameOverPanel.add(playAgainButton);
        gameOverPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        gameOverPanel.add(endGameButton);
        gameOverPanel.add(Box.createVerticalGlue());

        //An action listener that redirects to the instructions page in case the user wishes to play again
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the game and start over
                currentQuestionIndex = 0;
                score = 0;
                timeLeft = 180;
                showInstructionsPage();
            }
        });

        //An action listener that cancels and closes the window if the user does not wish to play another round
        endGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        getContentPane().removeAll();
        add(gameOverPanel);
        revalidate();
        repaint();
    }

    //Method that starts the timer when the game starts being played
    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                updateTimerLabel();
                if (timeLeft <= 0) {
                    timer.stop();
                    showGameOverScreen();
                }
            }
        });
        timer.start();
    }

    private void updateTimerLabel() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;
        timerLabel.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
    }

    public static void main(String[] args) {
        new Main(); // Call the Window so that the content is rendered
    }
}