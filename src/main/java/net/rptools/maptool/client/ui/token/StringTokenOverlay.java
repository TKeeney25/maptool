/*
 * This software Copyright by the RPTools.net development team, and
 * licensed under the Affero GPL Version 3 or, at your option, any later
 * version.
 *
 * MapTool Source Code is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public
 * License * along with this source Code.  If not, please visit
 * <http://www.gnu.org/licenses/> and specifically the Affero license
 * text at <http://www.gnu.org/licenses/agpl.html>.
 */
package net.rptools.maptool.client.ui.token;

import net.rptools.maptool.model.Token;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Draw an X over a token.
 *
 * @author tkeeney
 * @version $Revision: 0 $ $Date: 2021-09-28$ $Author:
 *     keeps25 $
 */
public class StringTokenOverlay extends BooleanTokenOverlay {

  /** Color for the X */
  private Color color;

  /** Stroke used to draw the line */
  private BasicStroke stroke;

  /** Text used for the drawing*/
  private String text;

  /** Default constructor needed for XML encoding/decoding */
  public StringTokenOverlay() {
    this(BooleanTokenOverlay.DEFAULT_STATE_NAME, Color.RED, 5, "");
  }

  /**
   * Create a X token overlay with the given name.
   *
   * @param aName Name of this token overlay.
   * @param aColor The color of this token overlay.
   * @param aWidth The width of the lines in this token overlay.
   */
  public StringTokenOverlay(String aName, Color aColor, int aWidth, String aText) {
    super(aName);
    if (aColor == null) aColor = Color.RED;
    color = aColor;
    if (aWidth <= 0) aWidth = 3;
    stroke = new BasicStroke(aWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
    if (aText == null) {
      text = "";
    }
    text = aText;
  }

  /**
   * @see BooleanTokenOverlay#paintOverlay(Graphics2D,
   *     Token, Rectangle)
   */
  @Override
  public void paintOverlay(Graphics2D g, Token aToken, Rectangle bounds) {
    Color tempColor = g.getColor();
    g.setColor(color);
    Stroke tempStroke = g.getStroke();
    g.setStroke(stroke);
    Composite tempComposite = g.getComposite();
    if (getOpacity() != 100)
      g.setComposite(
          AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) getOpacity() / 100));
    g.drawString(text, 0, bounds.height);
    g.setColor(tempColor);
    g.setStroke(tempStroke);
    g.setComposite(tempComposite);
  }

  /** @see BooleanTokenOverlay#clone() */
  @Override
  public Object clone() {
    BooleanTokenOverlay overlay = new StringTokenOverlay(getName(), getColor(), getWidth(), getText());
    overlay.setOrder(getOrder());
    overlay.setGroup(getGroup());
    overlay.setMouseover(isMouseover());
    overlay.setOpacity(getOpacity());
    overlay.setShowGM(isShowGM());
    overlay.setShowOwner(isShowOwner());
    overlay.setShowOthers(isShowOthers());
    return overlay;
  }

  /**
   * Get the color for this XTokenOverlay.
   *
   * @return Returns the current value of color.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Get the stroke for this XTokenOverlay.
   *
   * @return Returns the current value of stroke.
   */
  protected BasicStroke getStroke() {
    return stroke;
  }

  /**
   * Set the value of color for this XTokenOverlay.
   *
   * @param aColor The color to set.
   */
  public void setColor(Color aColor) {
    color = aColor;
  }

  /**
   * Get the width for this XTokenOverlay.
   *
   * @return Returns the current value of width.
   */
  public int getWidth() {
    return (int) stroke.getLineWidth();
  }

  public String getText() {return text;}

  /**
   * Set the value of width for this XTokenOverlay.
   *
   * @param aWidth The width to set.
   */
  public void setWidth(int aWidth) {
    if (aWidth <= 0) aWidth = 3;
    stroke = new BasicStroke(aWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
  }
}
