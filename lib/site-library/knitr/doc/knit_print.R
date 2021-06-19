<<<<<<< HEAD
## ----normal-print, comment=''--------------------------------------------
head(mtcars)

## ------------------------------------------------------------------------
=======
## ----normal-print, comment=''-------------------------------------------------
head(mtcars)

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
library(knitr)
knit_print  # an S3 generic function
methods(knit_print)
getS3method('knit_print', 'default')  # the default method
normal_print

<<<<<<< HEAD
## ----collapse=TRUE-------------------------------------------------------
knit_print(1:10)
knit_print(head(mtcars))

## ------------------------------------------------------------------------
=======
## ----collapse=TRUE------------------------------------------------------------
knit_print(1:10)
knit_print(head(mtcars))

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
library(knitr)
# define a method for objects of the class data.frame
knit_print.data.frame = function(x, ...) {
  res = paste(c('', '', kable(x)), collapse = '\n')
  asis_output(res)
}
<<<<<<< HEAD

## ----knit-print----------------------------------------------------------
=======
# register the method
registerS3method("knit_print", "data.frame", knit_print.data.frame)

## ----knit-print---------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
1 + 1
head(letters)
list(a = 1, b = 9:4)
head(mtcars)
cat('This is cool.')

<<<<<<< HEAD
## ----eval=FALSE, tidy=FALSE----------------------------------------------
=======
## ----eval=FALSE, tidy=FALSE---------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  knit_print.classA = function(x, ...) {
#    # ignore options and inline
#  }
#  knit_print.classB = function(x, options, ...) {
#    # use the chunk option out.height
<<<<<<< HEAD
#    asis_output(paste(
#      '<iframe src="https://yihui.name" height="', options$out.height, '"></iframe>',
=======
#    asis_output(paste0(
#      '<iframe src="https://yihui.org" height="', options$out.height, '"></iframe>',
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#    ))
#  }
#  knit_print.classC = function(x, inline = FALSE, ...) {
#    # different output according to inline=TRUE/FALSE
#    if (inline) {
#      'inline output for classC'
#    } else {
#      'chunk output for classC'
#    }
#  }
#  knit_print.classD = function(x, options, inline = FALSE, ...) {
#    # use both options and inline
#  }

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
dummy_print = function(x, ...) {
  cat("I do not know what to print!")
  # this function implicitly returns an invisible NULL
}

<<<<<<< HEAD
## ----knit-print, render=dummy_print, collapse=TRUE-----------------------
=======
## ----knit-print, render=dummy_print, collapse=TRUE----------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
1 + 1
head(letters)
list(a = 1, b = 9:4)
head(mtcars)
cat('This is cool.')

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
1 + 1
invisible(1 + 1)
invisible(head(mtcars))
x = 1:10  # invisibly returns 1:10

<<<<<<< HEAD
## ----eval=FALSE, tidy=FALSE----------------------------------------------
=======
## ----eval=FALSE, tidy=FALSE---------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  # pseudo code
#  knit_print.ggvis = function(x, ...) {
#    res = ggvis::print_this_object(x)
#    knitr::asis_output(res, meta = list(
#      ggvis = list(
#        version = '0.1.0',
#        js  = system.file('www', 'js',  'ggvis.js',  package = 'ggvis'),
#        css = system.file('www', 'www', 'ggvis.css', package = 'ggvis')
#      )
#    ))
#  }

<<<<<<< HEAD
## ----tidy=FALSE----------------------------------------------------------
library(knitr)
knit_print.foo = function(x, ...) {
  res = paste('**This is a `foo` object**:', x)
=======
## ----tidy=FALSE---------------------------------------------------------------
library(knitr)
knit_print.foo = function(x, ...) {
  res = paste('> **This is a `foo` object**:', x)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  asis_output(res, meta = list(
    js  = system.file('www', 'shared', 'shiny.js',  package = 'shiny'),
    css = system.file('www', 'shared', 'shiny.css', package = 'shiny')
  ))
}

<<<<<<< HEAD
## ------------------------------------------------------------------------
new_foo = function(x) structure(x, class = 'foo')
new_foo('hello')

## ------------------------------------------------------------------------
str(knit_meta(clean = FALSE))

## ------------------------------------------------------------------------
new_foo('world')

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
new_foo = function(x) structure(x, class = 'foo')
new_foo('hello')

## -----------------------------------------------------------------------------
str(knit_meta(clean = FALSE))

## -----------------------------------------------------------------------------
new_foo('world')

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
knit_print.bar = function(x, ...) {
  asis_output(x, meta = list(head = '<script>console.log("bar!")</script>'))
}
new_bar = function(x) structure(x, class = 'bar')
<<<<<<< HEAD
new_bar('**hello** world!')
new_bar('hello **world**!')

## ------------------------------------------------------------------------
str(knit_meta())
str(knit_meta()) # empty now, because clean = TRUE by default

## ------------------------------------------------------------------------
knitr::asis_output

## ----clean-up, include=FALSE---------------------------------------------
=======
new_bar('> **hello** world!')
new_bar('> hello **world**!')

## -----------------------------------------------------------------------------
str(knit_meta())
str(knit_meta()) # empty now, because clean = TRUE by default

## -----------------------------------------------------------------------------
knitr::asis_output

## ----clean-up, include=FALSE--------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
# R compiles all vignettes in the same session, which can be bad
rm(list = ls(all = TRUE))

