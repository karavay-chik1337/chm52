
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SimpleGUI extends JFrame {

    private static final int RENDER_QUALITY = 200;

    private static final JButton button = new JButton("Построить");
    private static final JTextField inputA = new JTextField("0", 10);
    private static final JTextField inputB = new JTextField("2", 10);
    private static final JTextField inputN = new JTextField("100", 10);
    private static final JLabel labelA = new JLabel("Левый конец отрезка(a): ");
    private static final JLabel labelB = new JLabel("Правый конец отрезка(b): ");
    private static final JLabel labelN = new JLabel("Количество разбиений(n): ");
    private static final JLabel text = new JLabel("");

    private String graphType;

    public SimpleGUI() {

        super("CHM5");
        this.setBounds(100, 100, 400, 250);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(9, 1, 2, 2));
        container.add(labelA);
        container.add(inputA);
        container.add(labelB);
        container.add(inputB);
        container.add(labelN);
        container.add(inputN);
        container.add(text);
        container.add(text);

        button.addActionListener(e -> {
                    Section section = new Section(
                            Double.parseDouble(inputA.getText()),
                            Double.parseDouble(inputB.getText()),
                            Integer.parseInt(inputN.getText())
                    );
                    Function function = new Function();
                    TypeMethod typeMethod = new TypeMethod(function);
                    JFreeChart[] graphs = createGraphs(section, function, typeMethod);
                    displayGraphs(graphs);
                }
        );
        container.add(button);
    }

    private void displayGraphs(JFreeChart[] graphs) {
        JFrame graphFrame = new JFrame(graphType);
        graphFrame.setSize(1500, 800);
        graphFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane();
        graphFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel graphPanel = new JPanel(new GridLayout(1, 3));
        scrollPane.setViewportView(graphPanel);

        for (JFreeChart graph : graphs) {
            ChartPanel chartPanel = new ChartPanel(graph, true);
            graphPanel.add(chartPanel);
        }
        graphFrame.setVisible(true);
    }

    private JFreeChart[] createGraphs(Section section, Function function, TypeMethod typeMethod) {

        XYSeries rungeFunctionU1 = new XYSeries("RungeU1");
        XYSeries rungeFunctionU2 = new XYSeries("RungeU2");
        XYSeries eulerFunctionU1 = new XYSeries("EulerU1");
        XYSeries eulerFunctionU2 = new XYSeries("EulerU2");
        double[] xValues = section.uniformPart();
        double[] yValueRU1 = typeMethod.runge(section.a, section.b, section.n)[0];
        double[] yValueRU2 = typeMethod.runge(section.a, section.b, section.n)[1];
        double[] yValueEU1 = typeMethod.euler(section.a, section.b, section.n)[0];
        double[] yValueEU2 = typeMethod.euler(section.a, section.b, section.n)[1];
        for (int i = 0; i < section.n; i++) {
            rungeFunctionU1.add(xValues[i], yValueRU1[i]);
            rungeFunctionU2.add(xValues[i], yValueRU2[i]);
            eulerFunctionU1.add(xValues[i], yValueEU1[i]);
            eulerFunctionU2.add(xValues[i], yValueEU2[i]);
        }

        NumberAxis xAxis = new NumberAxis("X");
        NumberAxis yAxis = new NumberAxis("Y");

        xAxis.setAutoRange(false);
        yAxis.setAutoRange(false);

        xAxis.setRange(xValues[0] - 1, xValues[xValues.length-1] + 1);
        yAxis.setRange(Math.min(yValueRU1[0], yValueRU2[0]) - 1, Math.max(yValueRU1[section.n - 1], yValueRU2[section.n - 1]) + 1);

        XYSeriesCollection bothFunc1 = new XYSeriesCollection();
        bothFunc1.addSeries(rungeFunctionU1);
        bothFunc1.addSeries(eulerFunctionU1);

        XYSeriesCollection bothFunc2 = new XYSeriesCollection();
        bothFunc2.addSeries(rungeFunctionU2);
        bothFunc2.addSeries(eulerFunctionU2);
        // Создаем графики
//        JFreeChart chartRungeU1 = ChartFactory.createXYLineChart("RungeU1", "X", "Y",
//                new XYSeriesCollection(rungeFunctionU1), PlotOrientation.VERTICAL, true, true, false);

        JFreeChart chartEulerU1 = ChartFactory.createXYLineChart("U1", "X", "Y",
                bothFunc1, PlotOrientation.VERTICAL, true, true, false);

//        JFreeChart chartRungeU2 = ChartFactory.createXYLineChart("Графики", "X", "Y",
//                bothFunc2, PlotOrientation.VERTICAL, true, true, false);

        JFreeChart chartEulerU2 = ChartFactory.createXYLineChart("U2", "X", "Y",
                bothFunc2, PlotOrientation.VERTICAL, true, true, false);


//        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
//        renderer1.setSeriesLinesVisible(0, true);
//        renderer1.setSeriesShapesVisible(0, true);
//        renderer1.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
//        renderer1.setSeriesPaint(0, Color.RED);
//
//        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
//        renderer2.setSeriesLinesVisible(0, true);
//        renderer2.setSeriesShapesVisible(0, true);
//        renderer2.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
//        renderer2.setSeriesPaint(0, Color.GREEN);

        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
        renderer3.setSeriesLinesVisible(0, true);
        renderer3.setSeriesShapesVisible(0, true);
        renderer3.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
        renderer3.setSeriesPaint(0, Color.BLUE);

        XYLineAndShapeRenderer renderer4 = new XYLineAndShapeRenderer();
        renderer4.setSeriesLinesVisible(0, true);
        renderer4.setSeriesShapesVisible(0, true);
        renderer4.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
        renderer4.setSeriesPaint(0, Color.BLUE);

//        chartRungeU1.getXYPlot().setDomainAxis(xAxis);
//        chartRungeU1.getXYPlot().setRangeAxis(yAxis);
//        chartRungeU1.getXYPlot().setRenderer(renderer1);
//        chartRungeU2.getXYPlot().setDomainAxis(xAxis);
//        chartRungeU2.getXYPlot().setRangeAxis(yAxis);
//        chartRungeU2.getXYPlot().setRenderer(renderer2);

        chartEulerU1.getXYPlot().setDomainAxis(xAxis);
        chartEulerU1.getXYPlot().setRangeAxis(yAxis);
        chartEulerU1.getXYPlot().setRenderer(renderer3);

        chartEulerU2.getXYPlot().setDomainAxis(xAxis);
        chartEulerU2.getXYPlot().setRangeAxis(yAxis);
        chartEulerU2.getXYPlot().setRenderer(renderer4);

        return new JFreeChart[] {
                chartEulerU1,
                chartEulerU2
        };
    }
}
