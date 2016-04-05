# 3-2-1 rotate

## Gridview with master detail flow

The purpose is to build out a skeleton project with a _master-detail 
flow_ pattern, but one that utilizes 3 states and a gridview.

* On phones, the default display with 2-column with gridview is to be 
displayed and a separate details activity.
* On tablets (sw600dp), the column width should increase to 3-columns.
* In landscape mode on tablets, the column width should drop to 
1-column, and the details fragment should show on the right.

Issues: at present it is mostly working. 

* Rotating a tablet from portrait to landscape from the details activity 
does not persist the view of the detail Right now I can tell the main 
activity is getting the data it needs, but it might be receiving it too 
early. I don't think the gridview is scrolling either.
* Rotating a tablet from landscape to portrait goes immediately to the
listing activity instead of the details activity. Ideally it should note
the _last_ item clicked and, if it was a listin in the master view, it
should go to the detail and persist the one viewed. Going back from 
there to the listing activity should persist the scroll location. 
