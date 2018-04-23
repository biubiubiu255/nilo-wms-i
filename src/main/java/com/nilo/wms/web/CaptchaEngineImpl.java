package com.nilo.wms.web;

import com.jhlabs.image.PinchFilter;
import com.jhlabs.math.ImageFunction2D;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByBufferedImageOp;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.Glyphs;
import com.octo.captcha.component.image.textpaster.GlyphsPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.GlyphsVisitors;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.OverlapGlyphsUsingShapeVisitor;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.TranslateGlyphsVerticalRandomVisitor;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import io.leopard.web.captcha.CaptchaEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CaptchaEngineImpl extends ImageCaptchaEngine implements CaptchaEngine {

    protected static final Log logger = LogFactory.getLog(CaptchaEngineImpl.class);

    private static final String words = "abcdefghijkmnpqrstwsyz2346789";

    private int width;
    private int height;

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Add a factory to the gimpy list
     *
     * @return true if added false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean addFactory(com.octo.captcha.image.ImageCaptchaFactory factory) {
        return factory != null && this.factories.add(factory);
    }

    @Override
    public void initialFactories() {
        WordGenerator dictionnaryWords = new RandomWordGenerator(words);
        // wordtoimage components
        TextPaster randomPaster = new GlyphsPaster(4, 5, new RandomListColorGenerator(

                new Color[]{ //
                        new Color(23, 170, 27),//
                        new Color(220, 34, 11), //
                        new Color(23, 67, 172) //
                }),

                new GlyphsVisitors[]{//
                        new TranslateGlyphsVerticalRandomVisitor(1),//

                        new OverlapGlyphsUsingShapeVisitor(3), //
                        new TranslateAllToRandomPointVisitor()
                        //new TranslateAllToRandomPointVisitor() //
                });
        /*
         * new TextVisitor[]{ new OverlapGlyphsTextVisitor(6) }, null
		 */

        // int width = this.getWidth();
        // int height = this.getHeight();
        BackgroundGenerator back = new UniColorBackgroundGenerator(width, height, Color.white);

        FontGenerator shearedFont = new RandomFontGenerator(50, 50, //
                new Font[]{new Font("Arial", Font.BOLD, 50), //
                        new Font("Bell MT", Font.PLAIN, 50), //
                        new Font("Cooper", Font.PLAIN, 50) //
                }, true);

        PinchFilter pinch = new PinchFilter();

        pinch.setAmount(-.5f);
        pinch.setRadius(30);
        pinch.setAngle((float) (Math.PI / 16));
        pinch.setCentreX(0.5f);
        pinch.setCentreY(-0.01f);
        pinch.setEdgeAction(ImageFunction2D.CLAMP);

        PinchFilter pinch2 = new PinchFilter();
        pinch2.setAmount(-.6f);
        pinch2.setRadius(70);
        pinch2.setAngle((float) (Math.PI / 16));
        pinch2.setCentreX(0.3f);
        pinch2.setCentreY(1.01f);
        pinch2.setEdgeAction(ImageFunction2D.CLAMP);

        PinchFilter pinch3 = new PinchFilter();
        pinch3.setAmount(-.6f);
        pinch3.setRadius(70);
        pinch3.setAngle((float) (Math.PI / 16));
        pinch3.setCentreX(0.8f);
        pinch3.setCentreY(-0.01f);
        pinch3.setEdgeAction(ImageFunction2D.CLAMP);

        List<ImageDeformation> textDef = new ArrayList<>();
        textDef.add(new ImageDeformationByBufferedImageOp(pinch));
        textDef.add(new ImageDeformationByBufferedImageOp(pinch2));
        textDef.add(new ImageDeformationByBufferedImageOp(pinch3));

        // word2image 1
        WordToImage word2image = new DeformedComposedWordToImage(false, shearedFont, back, randomPaster,
                new ArrayList<ImageDeformation>(), new ArrayList<ImageDeformation>(), textDef
        );

        this.addFactory(new GimpyFactory(dictionnaryWords, word2image, false));
    }

    static class TranslateAllToRandomPointVisitor implements GlyphsVisitors {
        private Random myRandom = new SecureRandom();
        private double horizontalMargins = 0.0D;
        private double verticalMargins = 0.0D;

        public TranslateAllToRandomPointVisitor() {
        }

        public TranslateAllToRandomPointVisitor(double horizontalmargins, double verticalmargins) {
            this.horizontalMargins = horizontalmargins;
            this.verticalMargins = verticalmargins;
        }

        public void visit(Glyphs glyphs, Rectangle2D backroundBounds) {
            double xRange = backroundBounds.getWidth() - glyphs.getBoundsWidth() - this.horizontalMargins;
            double yRange = backroundBounds.getHeight() - glyphs.getBoundsHeight() - this.verticalMargins;
            double tx = xRange * this.myRandom.nextDouble() - glyphs.getBoundsX() + this.horizontalMargins / 2.0D;
            double ty = yRange * this.myRandom.nextDouble() - glyphs.getBoundsY() + this.verticalMargins / 2.0D;
            glyphs.translate(tx / 2, ty);
        }
    }
}
