# CODE for EXERCISE 1
# -------------------
# 
# Import the Tkinter package
#
from tkinter import *

# This method is called when the button is pressed
def clicked():
    print("Clicked")

# Create a main frame with
#    - a title
#    - size 200 by 200 pixels
app = Tk()
app.title("GUI Example 1")
app.geometry('200x200')

# Create the button
#   - with suitable text
#   - a command to call when the button is pressed
button1 = Button(app, text="Click Here", command=clicked)

# Make the button visible at the bottom of the frame 
button1.pack(side='bottom')

# Start the application running
app.mainloop()
