# CODE for EXERCISE 4
# -------------------
# This exercise introduces  
#   * Canvas widget
#   * Event bindings
#
# The exercise aims are drawing on the canvas and
# using more advanced events

from tkinter import *

app = Tk() # Create the top-level window
app.title("GUI Example 4") # OPTIONALLY set the title
app.geometry('400x400')  # OPTIONALLY set the size

# Create a canvas
# -----------------
#   - background is blue (this is a bit odd)
#   - make it as large as the main window
w = Canvas(app, bg='blue') 
w.pack(expand = 1, fill = BOTH)


# Add some shapes and text
# ------------------------
#   - rectangle(x0, y0, x1, y1, ...):
#        top left corner at (x0, y0)
#        bottom right corner at (x1, y1)
#   - oval(x0, y0, x1, y1, ...), also used for circles
#   - text(x, y, text="...", ...)
w.create_rectangle(10, 10, 100, 50, outline="red", width = 5)
w.create_rectangle(50, 150, 100, 200, fill="yellow")
w.create_oval(140, 110, 200, 170, outline="green", width = 5)
w.create_text(200, 200, text = "Hello")

# Event bindings
# --------------
#   Bind mouse or key press events to function
#

# Handler for a key press event
#
def hKey(event):
    print("Lower case h key pressed")

# Bind the key press 'h' TO THE WINDOW
#   bind has a pattern and a function name
#   The pattern is "<KeyPress-h>"
#      ... works for any key
app.bind("<KeyPress-h>", hKey)

# Handler for a mouse click event
#
def mouseClick(event):
    print("Mouse click at x =", event.x, "y =", event.y)
    
# Bind the mouse click TO THE CANVAS
#   bind has a pattern and a function name
#   The pattern is "<Button-1>"
#      ... the different mouse buttons have difference numbers
w.bind("<Button-1>", mouseClick)


app.mainloop() # Start the main loop
