package visuals;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import main.ArrayVisualizer;
import utils.Highlights;
import utils.Renderer;

/*
 * 
MIT License

Copyright (c) 2019 w0rthy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 *
 */

final public class Bars {
    public void drawVisual(int[] array, ArrayVisualizer ArrayVisualizer, Renderer Renderer, Graphics2D mainRender, Graphics2D extraRender, Highlights Highlights) {
        for(int i = 0; i < ArrayVisualizer.getCurrentLength(); i++){
            if(Highlights.fancyFinishActive()) {
                if(i < Highlights.getFancyFinishPosition()) {
                    mainRender.setColor(Color.GREEN);
                }
                else if(ArrayVisualizer.rainbowEnabled() || ArrayVisualizer.colorEnabled()) { 
                    mainRender.setColor(Renderer.getIntColor(array[i], ArrayVisualizer.getCurrentLength()));
                }
                else mainRender.setColor(Color.WHITE);

                Renderer.drawFancyFinish(ArrayVisualizer.getLogBaseTwoOfLength(), i, Highlights.getFancyFinishPosition(), mainRender, ArrayVisualizer.colorEnabled(), ArrayVisualizer.rainbowEnabled());
            }
            else {
                if(ArrayVisualizer.rainbowEnabled() || ArrayVisualizer.colorEnabled()) {
                    mainRender.setColor(Renderer.getIntColor(array[i], ArrayVisualizer.getCurrentLength()));
                }
                else mainRender.setColor(Color.WHITE);

                if(ArrayVisualizer.getCurrentLength() != 2) {
                    Renderer.colorMarkedBars(ArrayVisualizer.getLogBaseTwoOfLength(), i, Highlights, mainRender, ArrayVisualizer.colorEnabled(), ArrayVisualizer.rainbowEnabled(), ArrayVisualizer.analysisEnabled());
                }
            }
            /*
            int markHeight = 0;
            Color currentColor = mainRender.getColor();
            if(currentColor == Color.BLACK || currentColor == Color.RED || currentColor == Color.BLUE) {
                markHeight = 5;
            }
            */
            
            int y = 0;
            int width = (int) (Renderer.getXScale() * (i + 1)) - Renderer.getOffset();

            if(ArrayVisualizer.rainbowEnabled()) {
                if(width > 0) {
                    mainRender.fillRect(Renderer.getOffset() + 20, 0, width, ArrayVisualizer.windowHeight());
                }
                
                Renderer.setOffset(Renderer.getOffset() + width);
            }
            else {
                if(width > 0) {
                    /*
                    int gap = 0;
                    if(width > 5) {
                        gap = 5;
                    }
                    */
                    
                    y = (int) (((ArrayVisualizer.windowHeight() - 20)) - (array[i] + 1) * Renderer.getYScale());
                    mainRender.fillRect(Renderer.getOffset() + 20, y /*- markHeight*/, width /*- gap*/, (int) ((array[i] + 1) * Renderer.getYScale()) /*+ markHeight*/);
                    
                    /*
                    double thickness = 1;
                    Stroke oldStroke = mainRender.getStroke();
                    mainRender.setStroke(new BasicStroke((float) thickness));
                    mainRender.setColor(Color.BLACK);
                    mainRender.drawLine(Renderer.getOffset() + 20, y, Renderer.getOffset() + 20, (int) Math.max(array[i] * Renderer.getYScale()-1, 1) + y);
                    mainRender.setStroke(oldStroke);
                    */
                }
                Renderer.setOffset(Renderer.getOffset() + width);
            }
        }
    }
}