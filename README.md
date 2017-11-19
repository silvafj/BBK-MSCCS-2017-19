# Worksheet on using the graphical library Tkinter

## Exercise 1: A First GUI Program

The following program is available in the repository (called `exercise1.py`). 
Find the program, open it using IDLE and run it.

```
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
```

### Exercise 1.1: Run the Program

Find the program (or type it in), open it using IDLE and run it. It should look like this:

![frames][fig1]

Also experiment with ‘usual’ windows operation such as resizing the window, minimising it and closing it.

### Exercise 1.2: Modify the Program

Although it has not been explained yet, see if you can figure out how to make the following modifications:

* Change the title
* Change the text in the button
* Change the text printed when the button is pressed
* Change the size (geometry) of the rectangular frame
* Move the button to the top of the frame

## Exercise 2: Adding a Label and Entry Widget

The aim of this exercise is to try out getting and setting attributes. The exercise is in two parts: at the end, your program should display the following:

![buttons][fig2]

The widgets are:

* Label: a single line text that is displayed
* Button: (see exercise 1)
* Entry: a single line text that can be entered

The intended behaviour is:

* Text is entered in entry widget
* When the button is pressed the label text is updated with the entered text

### Exercise 2.1: Part A: Getting and Setting Attributes

Run the given program (`exercise2A.py`); this version just has a button and a label. 
Pressing the button once changes the text of the label. 
Change it so that the text changes on each press, toggling between two messages.

### Exercise 2.2: Part B: Complete Program

Run the given program (`exercise2B.py`); this part adds the entry widget. 
When you enter text in the box (the `Entry` widget) and press the button, 
it only prints the text from the entry. Complete it so that it behaves as described above.

### Exercise 2.3: Extensions

* When the button is pressed, check if the entered text is blank (i.e. has zero length). 
  If so, do not copy it but instead set the background of the button red. 
  Restore the original background colour when the button is pressed and some text has been entered.
* After the button has been pressed and the label changed, make the next press of the button clear the text 
  in the entry widget. Change the button text so that the user understands what is happening.\
  

## Exercise 3: Managing Layout

The aim of this exercise is to learn more about layout using the ‘pack’ layout manager. 

The program `exercise3.py` displays the following (left hand picture shows the original display and the 
right hand side shows what happens when the window is resized):

![layout1][fig3]

The desired behaviour is shown below. The four labels are positioned at the corners and the labels 
fill the space when the window is resized.

![layout2][fig4]

### Exercise 3.1: Arrange the labels is a square grid

The pack layout manager introduces extra frames so that the labels are in the frames and the frames are 
in the top-level window. In the diagram above, the frames have a border so they can be seen.


### Exercise 3.2: Support resizing

Use the `expand` and `fill` attributes of the `pack` method to make the labels grow and expand 
into the available space. There is more guidance in code comments.

## Exercise 4: The Drawing Canvas and Events

The aim of this exercise is to learn about the drawing canvas and also to use two new types of events:

1. Key press events
2. Mouse click events

The program `exercise4.py` displays a canvas with some shapes:

![canvas][fig5]

In addition, two events are bound:

* Pressing the key ‘h’
* Clicking the left mouse button

### Exercise 4.1: Draw a Square where the mouse is clicked

Instead of always drawing the same shapes, use the mouse to draw a square: the top-left corner of the square goes where the mouse is clicked.

### Exercise 4.2: Change the shape, colour and fill

Use keys to specify the shape, colour and whether the shape is filled. For example:

* Shape: ‘s’ for square, ‘c’ for circle
* Filling: ‘F’ for filled, ‘f’ for unfilled
* Colour: ‘y’ for yellow, ‘r’ for red

## Exercise 5: Dialogs and Menu

The aim of this exercise is to use dialogs and menus.
 
* A dialog displays a window requiring a response before the user can continue. 
  We will look at message boxes and a file choosing dialog.
* A menu shows a menubar with menu items that expand when clicked to offer some command.

The program `example5.py` displays a frame, with two menu items.

![menu][fig6]

It does not do much yet! Change it to a simple application that can read a file, 
convert it to upper case and save it again. The commands are:

| Menu  | Command  | Description |
|---------------|----------------|----------------|
| File   |   Open   | Open a new file |
|     |   Save   | Save the existing file |
|     | Quit | Quit the application |
| Edit   | Convert to upper | Convert the file contents to upper case |
| About | Help | Useful help |
|     | About | Information about the application |


### Exercise 5.1: Add menu items

Add the new menu and menu items. At first, **do not** provide a command backing the menu item.

### Exercise 5.2: Implement Functions

Implement the functions to act on the command. You can use

* `open(filename, mode)` to open the file with mode `'r'` and `'w'`
* `f.close()` to close a file
* `f.read()` to read a whole file
* `s.upper()` to convert a string `s` to uppercase (returns a new string)
* `f.write(string)` to write a `string` to the file.

### Exercise 5.3: Add checks

Add checks so that 

+ the program never crashes (!), and 
+ the user does not lose work. 

The following table suggest which checks are needed. Display suitable messages in each case.

| Command  | Checks required  |
|---------------|----------------|
| Open    |  Check for unsaved changes to the current file (Question) |
| Save   |  Check a file is open (Error)  |
|        | Check that changes need saving (Info) |
| Quit   | Check for unsaved changes to the current file (Question) |
| Convert to upper | Check a file is open (Error) |

## Exercise 6: Radio Button

The aim of this exercise is to use a radio button. The program `exercise6.py` is shown below:
```
from tkinter import *

root = Tk()

# Method handles radio button selection
#    * Create a string showing which choice selected
#    * Use this in the label
def sel():
   selection = "You selected the option " + str(var.get())
   label["text"] = selection

# Integer control variable 
#   Holds the selection of the radio button 
var = IntVar()

# Create 2 radio buttons. Each has the same control variable
#
R1 = Radiobutton(root, text="Option 1", variable=var, value=1,
                  command=sel)
R1.pack( anchor = W )

R2 = Radiobutton(root, text="Option 2", variable=var, value=2,
                  command=sel)
R2.pack( anchor = W )


# Create a label. Intially blank
label = Label(root)
label.pack()

# Start the main loop
root.mainloop()

```
* There are three radio buttons
* Selecting one of the buttons changes the text of the label.

![radio][fig7]

### Exercise 6.1: Enhanced the program

The complete program should change the colour of the button is well as the text, 
for a suitable choice of three (or more) colours.

![radio][fig8]

## 7: Additional Notes (no exercises)

### 7.1 Layout

When a widget is created it needs to be positioned inside its parent. 
The positioning also needs to consider the possibility that the window may be resized. 
Layout is the responsibility of a layout manager: Tkinter offers a choice of layout managers.

These exercises have utilised the `pack` layout manager. It is simplest to get started with and also behaves 
reasonably with respect to resizing. Other layout managers are `place` and `grid`; 
the latter is often recommended for more complex layouts.

The following two example program fragments illustrate the principles:
```
#
# Create three labels of given width
#
bA = Label(app, text="A", width=12, bg='red')
bB = Label(app, text="B", width=12, bg='yellow') 
bC = Label(app, text="C", width=12, bg='blue')

# Pack horizontally
# -----------------
# Horizontal packing with
#  side = "left"
#  side = "right"

bA.pack(side='left') 
bB.pack(side='left') 
bC.pack(side='left')
```
results in the display:

![screen][fig9]

Whereas the vertical packing:
```
# Pack vertically
# ---------------
# Vertical packing with
#  side = "top"
#  side = "bottom"
#
bA.pack(side='top') 
bB.pack(side='top') 
bC.pack(side='top')
```
displays:

![screen][fig10]

There are two ways to control layout further using pack:

1. Create extra frames to hold widgets. For example, to position widget in the four quadrants of a square, 
   create frames for the two and bottom halves and pack these vertically, then pack the widgets 
   horizontally into the two frames.
2. Use the ‘expand’ and ‘fill’ attributes of pack.
	* Fill (values X, Y, BOTH) determines the direction in which a widget can grow to fill available space.
	* Expand (integer value) determines whether the containing frame gives more space to the widgets it contains. 
	  A zero value means no expansion. A positive value determine the relative expansion of each widget.


### 7.2 The Top-Level Window

A *top-level window* is a window that has an independent existence under the window manager. 
It has the standard icons for closing or minimising the window, and can be moved and resized independently. 
Your application can use any number of top-level windows.

A top level window can conveniently be created:

#### Attributes

| attribute  | meaning  |
|---------------|----------------|
| `bg` or `background`    |   The background color of the window.   |
| `menu`    |   To provide this window with a top-level menubar, supply a `Menu` widget as the value of this option.  |

#### Methods

| method name  | meaning  |
|---------------|----------------|
| `.geometry(newGeometry=None)`   |   Set the window geometry. For example: `200x400`, `200x400+10+20`, `200x400-10-20`. If the argument is omitted, the current geometry string is returned.  |
| `.title(text=None)`   |  Set the window title. If the argument is omitted, returns the current title.   |

### 7.3 Message Dialogs

The following message dialogs can prove useful:

![dialogs][fig11]

On messages with a choice, the default choice is the first one. Use the `default` option:
```
default = C
```

to change this where `C = CANCEL`, `IGNORE`, `OK`, `NO`, `RETRY`, or `YES`


Each of the `ask...` pop-up functions returns a value that depends on which button the user pushed to 
remove the pop-up.

* `askokcancel`, `askretrycancel`, and `askyesno` all return a bool value: `True` for `OK` or `Yes` choices, 
   `False` for `No` or `Cancel` choices.
* `askquestion` returns `yes` for `Yes`, or `no` for `No`.

-----------------

Thanks to TEACHING LONDON COMPUTING: A RESOURCE HUB FROM CAS LONDON for the basis of this worksheet.


[fig1]: images/fig1.png "title, frame, and button"
[fig2]: images/fig2.png "entry, button, and label"
[fig3]: images/fig3.png "Layout 1"
[fig4]: images/fig4.png "Layout 2"
[fig5]: images/canvas1.png "Canvas example"
[fig6]: images/menu1.png "Menu example"
[fig7]: images/radio1.png "Radio buttons example 1"
[fig8]: images/radio2.png "Radio buttons example 2"
[fig9]: images/screen1.png "Pack example"
[fig10]: images/screen2.png "Pack example II"
[fig11]: images/dialogs.png "Example dialogs"
