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

# Event bindings
# --------------
#   Bind mouse or key press events to function
#

shape = "rectangle"
shape_filled = False
shape_colour = "yellow"

# Handler for a key press event
#
def squareKey(event):
    global shape
    print("Lower case s key pressed")
    shape = "rectangle"

def circleKey(event):
    global shape
    print("Lower case c key pressed")
    shape = "circle"

def filledKey(event):
    global shape_filled
    print("Lower case F key pressed")
    shape_filled = True

def unfilledKey(event):
    global shape_filled
    print("Lower case f key pressed")
    shape_filled = False

def yellowKey(event):
    global shape_colour
    print("Lower case y key pressed")
    shape_colour = "yellow"

def redKey(event):
    global shape_colour
    print("Lower case r key pressed")
    shape_colour = "red"

# Bind the key press TO THE WINDOW
#   bind has a pattern and a function name
#   The pattern is "<KeyPress-h>"
#      ... works for any key
app.bind("<KeyPress-s>", squareKey)
app.bind("<KeyPress-c>", circleKey)
app.bind("<KeyPress-F>", filledKey)
app.bind("<KeyPress-f>", unfilledKey)
app.bind("<KeyPress-y>", yellowKey)
app.bind("<KeyPress-r>", redKey)

# Handler for a mouse click event
#
def mouseClick(event):
    print("Mouse click at x =", event.x, "y =", event.y)
    if shape == "rectangle":
        w.create_rectangle(event.x, event.y, event.x + 100, event.y + 100,
                           fill=shape_colour if shape_filled else None,
                           outline=shape_colour, width = 2)
    elif shape == "circle":
        w.create_oval(event.x, event.y, event.x + 100, event.y + 100,
                      fill=shape_colour if shape_filled else None,
                      outline=shape_colour, width = 2)

# Bind the mouse click TO THE CANVAS
#   bind has a pattern and a function name
#   The pattern is "<Button-1>"
#      ... the different mouse buttons have difference numbers
w.bind("<Button-1>", mouseClick)


app.mainloop() # Start the main loop
