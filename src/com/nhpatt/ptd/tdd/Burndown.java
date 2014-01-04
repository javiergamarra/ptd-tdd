package com.nhpatt.ptd.tdd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Burndown extends JFrame {

	private static final int SIZE = 500;
	private static final int MAX_WIDTH = SIZE - 30;
	private static final int MAX_HEIGHT = SIZE - 50;
	private static final long serialVersionUID = 1L;

	public static void main(final String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final Burndown burndown = new Burndown(Sprint.exampleSprint());
				burndown.setVisible(true);
			}
		});
	}

	public Burndown(final Sprint sprint) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new Surface(sprint));
		setSize(SIZE, SIZE);
	}

	private class Surface extends JPanel {

		private final Sprint sprint;
		private static final long serialVersionUID = 1L;

		public Surface(final Sprint sprint) {
			this.sprint = sprint;
		}

		private void doDrawing(final Graphics graphics) {
			final Graphics2D g2d = (Graphics2D) graphics;

			drawAxis(g2d);

			g2d.setColor(Color.red);
			for (int i = 0; i <= sprint.getDays(); i++) {
				final double progress = sprint.normalizedProgress(sprint
						.getIdealProgress(i));
				g2d.drawOval(10 + MAX_WIDTH * i / sprint.getDays(),
						(int) (MAX_HEIGHT * progress), 4, 4);
			}

			g2d.setColor(Color.green);
			for (int i = 0; i <= sprint.getDays(); i++) {
				final double progress = sprint.normalizedProgress(sprint
						.getRealProgress(i).doubleValue());
				g2d.drawOval(10 + MAX_WIDTH * i / sprint.getDays(),
						(int) (MAX_HEIGHT * progress), 4, 4);
			}
		}

		private void drawAxis(final Graphics2D g2d) {
			g2d.setColor(Color.black);
			g2d.drawLine(10, 10, 10, MAX_HEIGHT);
			g2d.drawLine(10, MAX_HEIGHT, MAX_WIDTH, MAX_HEIGHT);
		}

		@Override
		public void paintComponent(final Graphics graphics) {
			super.paintComponent(graphics);
			doDrawing(graphics);
		}
	}

}
