using System;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace UI
{
	public class UIElement
	{
		protected MouseManager m;
		protected Rectangle bound;
		protected Color color;

		public event EventHandler Clicked;

		public UIElement (Vector2 offset ,Rectangle b, Color c)
		{
			m = new MouseManager();
			m.LeftButtonClicked += new EventHandler(click);

			color = c;
			bound = new Rectangle((int)(b.X + offset.X), (int)(b.Y + offset.Y), b.Width, b.Height);
		}

		public virtual void Update ()
		{
			m.Update();
		}
		public virtual void Draw(GraphicsDevice graphicsDevice, SpriteBatch spriteBatch)
		{
			var texture = new SolidColorTexture(graphicsDevice, color);
			spriteBatch.Draw(texture, bound, Color.White);
		}
		private void click (object sender, EventArgs e)
		{
			if (Clicked != null && bound.Contains (m.MousePosition)) {
				onClick();
				Clicked (this, null);
			}
		}
		public virtual void onClick()
		{
			//Should be overidden by UI Elements
		}

	}
}

