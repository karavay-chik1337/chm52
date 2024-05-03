//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.Ellipse2D;
//
//public class SimpleGUI extends JFrame {
//
//    private static final int RENDER_QUALITY = 200;
//
//    private static final JButton button = new JButton("Построить");
//    private static final JTextField inputA = new JTextField("-10", 10);
//    private static final JTextField inputB = new JTextField("10", 10);
//    private static final JTextField inputN = new JTextField("100", 10);
//    private static final JLabel labelA = new JLabel("Левый конец отрезка(a): ");
//    private static final JLabel labelB = new JLabel("Правый конец отрезка(b): ");
//    private static final JLabel labelN = new JLabel("Количество разбиений(n): ");
//    private static final JLabel text = new JLabel("");
//
//    private String graphType;
//
//    public SimpleGUI() {
//
//        super("CHM4");
//        this.setBounds(100, 100, 400, 250);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
////        buttonGroup1.add(inputSeparation1);
////        buttonGroup1.add(inputSeparation2);
////
////        buttonGroup2.add(inputMethod1);
////        buttonGroup2.add(inputMethod2);
////
////        inputSeparation1.doClick();
////        inputMethod1.doClick();
//
//        Container container = this.getContentPane();
//        container.setLayout(new GridLayout(9, 1, 2, 2));
//        container.add(labelA);
//        container.add(inputA);
//        container.add(labelB);
//        container.add(inputB);
//        container.add(labelN);
//        container.add(inputN);
////        container.add(labelSeparation);
//        container.add(text);
////        container.add(inputSeparation1);
////        container.add(inputSeparation2);
//        container.add(text);
////        container.add(labelMethod);
////        container.add(inputMethod1);
////        container.add(inputMethod2);
//
//        button.addActionListener(e -> {
//                    Section section = new Section(
//                            Double.parseDouble(inputA.getText()),
//                            Double.parseDouble(inputB.getText()),
//                            Integer.parseInt(inputN.getText())
//                    );
////                    Function<Double, Double> function = inputMethod1.isSelected() ? Lagrange.func() : Spline.func();
////                    Function<Double, Double> interpol = inputMethod1.isSelected() ? Lagrange.createLagrangePolynomial(section)
////                            : Spline.createCubicSpline(section);
////
////                    this.graphType = inputMethod1.isSelected() ? inputMethod1.getText() : inputMethod2.getText();
//                    //JFreeChart[] graphs = createGraphs(section, function, interpol);
//                    //displayGraphs(graphs);
//                }
//        );
//        container.add(button);
//    }
//
//    private void displayGraphs(JFreeChart[] graphs) {
//        JFrame graphFrame = new JFrame(graphType);
//        graphFrame.setSize(1600, 1200);
//        graphFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//
//        JScrollPane scrollPane = new JScrollPane();
//        graphFrame.add(scrollPane, BorderLayout.CENTER);
//
//        JPanel graphPanel = new JPanel(new GridLayout(1, 3));
//        scrollPane.setViewportView(graphPanel);
//
//        for (JFreeChart graph : graphs) {
//            ChartPanel chartPanel = new ChartPanel(graph, true);
//            graphPanel.add(chartPanel);
//        }
//        graphFrame.setVisible(true);
//    }
//
//    private JFreeChart[] createGraphs(Section section) {
//
//        XYSeries sourceFunction = new XYSeries("f(x)");
//        XYSeries interpolatedFunction = new XYSeries("interpolated_func(x)");
//        XYSeries error = new XYSeries("accuracy(x)");
//        double[] xValues = section.separation(section.xGenerate());
//        var maxFYAxis = Double.MIN_VALUE;
//        var minFYAxis = Double.MAX_VALUE;
//        for (double xValue : xValues) {
//            var yValue = func.apply(xValue);
//            if (yValue > maxFYAxis) {
//                maxFYAxis = yValue;
//            }
//            if (yValue < minFYAxis) {
//                minFYAxis = yValue;
//            }
//            var interFuncValue = interpol.apply(xValue);
//            var errXValue = Math.abs(yValue - interFuncValue);
//
//            sourceFunction.add(xValue, yValue);
//            interpolatedFunction.add(xValue, interFuncValue);
//            error.add(xValue, errXValue);
//        }
//
//        NumberAxis xAxis = new NumberAxis("X");
//        NumberAxis yAxis = new NumberAxis("Y");
//
//        xAxis.setAutoRange(false);
//        yAxis.setAutoRange(false);
//
//        xAxis.setRange(xValues[0] - 1, xValues[xValues.length-1] + 1);
//        yAxis.setRange(minFYAxis - 1, maxFYAxis + 1);
//
//        // Создаем графики
//        JFreeChart chartF = ChartFactory.createXYLineChart("f(x)", "X", "Y",
//                new XYSeriesCollection(sourceFunction), PlotOrientation.VERTICAL, true, true, false);
//
//        JFreeChart chartInterFunc = ChartFactory.createXYLineChart("interpolated_func(x)", "X", "Y",
//                new XYSeriesCollection(interpolatedFunction), PlotOrientation.VERTICAL, true, true, false);
//
//        JFreeChart chartErr = ChartFactory.createXYLineChart("accuracy(x)", "X", "Y",
//                new XYSeriesCollection(error), PlotOrientation.VERTICAL, true, true, false);
//
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
//
//        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
//        renderer3.setSeriesLinesVisible(0, true);
//        renderer3.setSeriesShapesVisible(0, true);
//        renderer3.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
//        renderer3.setSeriesPaint(0, Color.BLUE);
//
//        chartF.getXYPlot().setDomainAxis(xAxis);
//        chartF.getXYPlot().setRangeAxis(yAxis);
//        chartF.getXYPlot().setRenderer(renderer1);
//
//        chartInterFunc.getXYPlot().setDomainAxis(xAxis);
//        chartInterFunc.getXYPlot().setRangeAxis(yAxis);
//        chartInterFunc.getXYPlot().setRenderer(renderer2);
//
//        NumberAxis eXAxis = new NumberAxis("X");
//        NumberAxis eYAxis = new NumberAxis("Y");
//        eXAxis.setAutoRange(false);
//        eXAxis.setRange(xValues[0] - 1, xValues[xValues.length-1] + 1);
//        eYAxis.setAutoRange(true);
//
//        chartErr.getXYPlot().setDomainAxis(xAxis);
//        chartErr.getXYPlot().setRangeAxis(eYAxis);
//        chartErr.getXYPlot().setRenderer(renderer3);
//
//        return new JFreeChart[] {
//                chartF,
//                chartInterFunc,
//                chartErr
//        };
//    }
//}
