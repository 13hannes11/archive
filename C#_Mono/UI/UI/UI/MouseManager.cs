using System;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework;

namespace UI
{
	public class MouseManager
	{
		public event EventHandler LeftButtonClicked;
		public event EventHandler LeftButtonReleased;
		bool leftButtonLastState;
		Point lastClickedPos;
		public Point LastClickedPos {
			get {return lastClickedPos;}}

		public Point MousePosition {
			get{
				MouseState mState = Mouse.GetState();
				return new Point(mState.X, mState.Y);
			}
		}

		public MouseManager ()
		{
			leftButtonLastState = false;
		}

		public void Update ()
		{
			//If Clicked
			if (Mouse.GetState ().LeftButton == ButtonState.Pressed && !leftButtonLastState) {
				if (LeftButtonClicked != null)
            		LeftButtonClicked(this, null);
				leftButtonLastState = true;
				lastClickedPos = MousePosition;
			}
			//If Released
			if(Mouse.GetState ().LeftButton == ButtonState.Released && leftButtonLastState)
			{
				if (LeftButtonReleased != null)
            		LeftButtonReleased(this, null);
				leftButtonLastState = false;
				lastClickedPos = Point.Zero;
			}
		}
	}
}

