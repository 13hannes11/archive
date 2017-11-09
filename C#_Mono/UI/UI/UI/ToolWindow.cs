using System;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.Collections.Generic;

namespace UI
{
	public class ToolWindow
	{
		protected List<UIElement> uiElements;
		Button minimizeButton;

		int windowBarHeight;
		Vector2 pos;
		Vector2 size;
		Boolean minimized;
		protected Vector2 offset;

		public ToolWindow (Vector2 pos, Vector2 size)
		{
			uiElements = new List<UIElement>();
			windowBarHeight = 20;
			offset = new Vector2(pos.X, pos.Y + windowBarHeight);
			minimized = false;
			this.pos = pos;
			this.size = size;
			minimizeButton = new Button(new Vector2(2,2), new Rectangle((int)pos.X, (int) pos.Y, windowBarHeight - 4, windowBarHeight -4), Color.Red, 2, Color.Black);
			minimizeButton.Clicked += new EventHandler(minimize);
		}
		public void Update ()
		{
			minimizeButton.Update();
			foreach (var element in uiElements) {
				element.Update();
			}
		}
		public void DrawStatic (GraphicsDevice graphicsDevice, SpriteBatch spriteBatch)
		{
			var headBarTexture = new SolidColorTexture(graphicsDevice, Color.DarkGray);
			var bodyTexture = new SolidColorTexture(graphicsDevice, Color.DimGray);

			spriteBatch.Begin();
				spriteBatch.Draw(headBarTexture, new Rectangle((int)pos.X, (int)pos.Y, (int)size.X, windowBarHeight), Color.White);
				minimizeButton.Draw(graphicsDevice, spriteBatch);
			if (!minimized) {
				spriteBatch.Draw(bodyTexture, new Rectangle((int)pos.X, (int)pos.Y + windowBarHeight, (int)size.X,(int)size.Y - windowBarHeight), Color.White);
				//Draw all UI Elements
				foreach (var element in uiElements) {
					element.Draw(graphicsDevice, spriteBatch);
				}
			}
			spriteBatch.End();
		}
		protected void minimize(object sender, EventArgs e)
		{
			minimized = !minimized;
		}
	}
}

