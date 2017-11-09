using System;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace UI
{
	public class Button : UIElement
	{
		protected Color hColor {
			get{ return new Color(Color.Black, 60); }}
		protected Color innerColor;
		protected int borderSize;

		public Button (Vector2 offset, Rectangle b, Color c, int borderThickness, Color bColor): base(offset, b, bColor)
		{
			innerColor = c;
			borderSize = borderThickness;
		}
		public override void Update()
		{
			base.Update();
		}
		public override void Draw(GraphicsDevice graphicsDevice, SpriteBatch spriteBatch)
		{
			base.Draw(graphicsDevice, spriteBatch);
			var texture = new SolidColorTexture(graphicsDevice, innerColor);
			var hTexture = new SolidColorTexture(graphicsDevice, hColor);
			Rectangle inner = new Rectangle(bound.X + borderSize, bound.Y + borderSize , bound.Width - 2*borderSize, bound.Height - 2* borderSize);

			spriteBatch.Draw(texture, inner, Color.White);
			if(bound.Contains(m.MousePosition))
				spriteBatch.Draw(hTexture, inner, Color.White);

		}
	}
}

