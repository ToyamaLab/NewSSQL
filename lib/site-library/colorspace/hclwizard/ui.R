# -------------------------------------------------------------------
# - NAME:        ui.R
# - AUTHOR:      Reto Stauffer
# - DATE:        2015-05-01
# -------------------------------------------------------------------
# - DESCRIPTION: This is the shiny guitar tab stuff user interface.
# -------------------------------------------------------------------
# - EDITORIAL:   2015-05-01, RS: Created file on thinkreto.
# -------------------------------------------------------------------
<<<<<<< HEAD
# - L@ST MODIFIED: 2016-11-16 10:03 on thinkreto
=======
# - L@ST MODIFIED: 2019-01-13 11:36 on marvin
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
# -------------------------------------------------------------------


library("shiny")
library("shinyjs")

# - Configuration
ticks <- FALSE # to show ticks or not to show ticks

# Note: these should be set to the config of the
# first (automatically loaded) palette. If not, the
# functionality will be the same, however, the color
# map will be drawn a few times whlie the script sets
# the new Sliders.
<<<<<<< HEAD
default = list("N"  =   7,
               "H1" = 340,
               "H2" = 128,
               "C1" =  45,
               "C2" =  90,
               "L1" =  35,
               "L2" =  95,
               "P1" = 0.7,
               "P2" = 1.3,
               "LEV" = 3)
# Take default argument if set
if ( nchar(Sys.getenv("hclwizard_Ninit")) > 0 &
     is.numeric(as.numeric(Sys.getenv("hclwizard_Ninit"))) )
   default$N <- as.numeric(Sys.getenv("hclwizard_Ninit"))


# Define UI for application that draws a histogram
shinyUI(fluidPage(
   theme = "hclwizard.css",
   useShinyjs(),
=======
default = list("N"    =   7,
               "H1"   = 340,
               "H2"   = 128,
               "C1"   =  45,
               "CMAX" =  0,
               "C2"   =  90,
               "L1"   =  35,
               "L2"   =  95,
               "P1"   = 0.7,
               "P2"   = 1.3)
# Take default argument if set
default$N <- colorspace:::.colorspace_get_info("hclwizard_ninit")

# Hide return-to-R button on webserver
if ( Sys.info()["nodename"] == "sculptor.uberspace.de" ) {
    xtra <- "$(\"#closeapp\").remove();"
} else { xtra <- "" }

# Define UI for application that draws a histogram
shinyUI(fluidPage(
   tags$head(
      tags$link(rel = "stylesheet", type = "text/css", href = "hclwizard.css"),
      tags$link(rel = "stylesheet", type = "text/css", href = "hclwizard_darkmode.css"),
      # Register palette with user-defined name: only allow
      # alphanumeric values, spaces, underscores, and dashes.
      HTML("<script>
           $(document).ready(function() {
              $(\"#registerpalettebutton\").attr(\"disabled\", \"disabled\");
              $(\"#registerpalettename\").keyup(function(e) {
                 e.preventDefault()
                 this.value = this.value.replace(/[^A-Za-z0-9_\\s\\-]/gi, \"\");
                 if ( this.value.length == 0 ) {
                     $(\"#registerpalettebutton\").attr(\"disabled\", \"disabled\");
                 } else {
                     $(\"#registerpalettebutton\").removeAttr(\"disabled\");
                 }
              });
              //$(\"#registerpalettebutton\").click(function(e) {
              //   var val = $(\"#registerpalettename\").val();
              //   if ( val.length == 0 ) {
              //       ...
              //   }
              //});\n", xtra, "\n",
           "});
           </script>")
       
   ),
   useShinyjs(),
   div(class = "version-info", htmlOutput("version_info")),
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

   # ----------------------------------------------------------------
   # ----------------------------------------------------------------
   sidebarPanel(

      # -------------------------------------------------------------
      # Copy the line below to make a select box 
      # -------------------------------------------------------------
      h3("Base Options"),
<<<<<<< HEAD
      withTags(div(class="hcl-selectoptions",id="hcl-typ",
         selectInput("typ", label = h3("Nature of your data"), 
           choices = list(
               "Diverging"                  = "dive",
               "Qualitative"                = "qual",
               "Sequential (single hue)"    = "seqs",
               "Sequential (multiple hues)" = "seqm",
               #"Multi hue alert"       = "alrt",
               "R default schemes"          = "base"), 
           selected = 1)
      )),
      # Copy the line below to make a select box 
      withTags(div(class="hcl-selectoptions",id="hcl-PAL",
         #selectInput("PAL", label = h3("Base color scheme"),
         #  choices = list())
         selectizeInput("PAL",label=h3("Base color scheme"),
=======
      withTags(div(class = "hcl-selectoptions", id = "hcl-typ", 
         selectInput("typ", label = h3("Type of palette"), 
           choices = list(
               "Basic: Qualitative"                 = "qual",
               "Basic: Sequential (single-hue)"     = "seqs",
               "Basic: Sequential (multi-hue)"      = "seqm",
               "Basic: Diverging"                   = "dive",
               "Advanced: Sequential (single-hue)"  = "seqs_advanced",
               "Advanced: Sequential (multi-hue)"   = "seqm_advanced",
               "Advanced: Diverging"                = "dive_advanced",
               "R default schemes"                  = "base"), 
           selected = "seqm")
      )),
      # Copy the line below to make a select box 
      withTags(div(class = "hcl-selectoptions", id = "hcl-PAL", 
         #  choices = list())
         selectizeInput("PAL", label = h3("Base color scheme"),
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            choices = list(),
            options = list(create = TRUE,
               render = I('{
                  option: function(item, escape) {
<<<<<<< HEAD
                    // your own code to generate HTML here for each option item
                     var tmp = item.value
                     var imgname = tmp.toLowerCase().split(" ").join("_");
                     imgname = "images/pal_"+imgname+".png";
                     ////console.log( "<img src=\'"+imgname+"\'>"+imgname+"</img>" )
=======
                     // custom code to generate HTML before each option
                     var tmp = item.value
                     var imgname = tmp.toLowerCase().split(" ").join("_");
                     imgname = "images/pal_"+imgname+".png";
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                     return( "<img class=\'select-pal\' src=\'"+imgname+"\'></img>" )
                  }
               }')
            )
         )
      )),
      # Examples
<<<<<<< HEAD
      withTags(div(class="hcl-selectoptions",id="hcl-EXAMPLE",
=======
      withTags(div(class = "hcl-selectoptions", id = "hcl-EXAMPLE", 
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         selectInput("EXAMPLE", label = h3("Example"),
           choices = list())
      )),


      # -------------------------------------------------------------
      # The checkboxes for additional options
      # -------------------------------------------------------------
      h3("Control Options"),
<<<<<<< HEAD
      checkboxInput("reverse", "Reverse", value=FALSE, width=NULL),
      checkboxInput("fixup", "Correct colors", value=TRUE, width=NULL),
      checkboxInput("desaturate", "Desaturated", value=FALSE, width=NULL),
      radioButtons("constraint", "Vision", 
               choices = c("Normal","Deutan","Protan","Tritan"),
               selected="Normal"),
=======
      checkboxInput("reverse", "Reverse", value = FALSE, width = NULL),
      checkboxInput("fixup", "Correct colors", value = TRUE, width = NULL),
      checkboxInput("darkmode", "Dark mode", value = FALSE, width = NULL),
      checkboxInput("desaturate", "Desaturated", value = FALSE, width = NULL),
      radioButtons("constraint", "Vision", 
               choices  =  c("Normal", "Deutan", "Protan", "Tritan"), 
               selected = "Normal"),
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

      width = 2
   ),

   # ----------------------------------------------------------------
   # ----------------------------------------------------------------
   sidebarPanel(

      h3("Color Settings"),

      # -------------------------------------------------------------
      # - Show HUE
      # -------------------------------------------------------------
      htmlOutput("colorSliders"),
<<<<<<< HEAD
      #sliderInput('H1', 'HUE 1', -360, 360, 0, step = NULL, round = TRUE,
      withTags(div(class="hcl-sliderwrapper", id="H1-wrapper",
         withTags(span(class="hcl-slider",
            sliderInput('H1', label=NULL, -360, 360, default$H1, step = NULL,
                        round = TRUE, ticks = ticks, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class="hcl-slidervalue",
            textInput('H1val', label="HUE 1", width = NULL),
            actionButton("H1set","SET")
         ))
      )),
      withTags(div(class="hcl-sliderwrapper", id="H2-wrapper",
         withTags(span(class="hcl-slider",
            sliderInput('H2', label=NULL, -360, 360, default$H2, step = NULL,
                        round = TRUE, ticks = ticks, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class="hcl-slidervalue",
            textInput('H2val', label="HUE 2", width = NULL),
            actionButton("H2set","SET")
         ))
=======
      withTags(div(class = "hcl-sliderwrapper", id = "H1-wrapper",
         withTags(span(class = "hcl-slider",
            sliderInput("H1", label = NULL, -360, 360, default$H1, step = NULL,
                        round = TRUE, ticks = ticks, animate = FALSE,
                        width = NULL, sep  =  ",", pre = NULL, post = NULL)
         )),
         withTags(span(class = "hcl-slidervalue",
            textInput("H1val", label = "HUE 1", width = NULL),
            actionButton("H1set","SET")
         )),
         withTags(div(class = "hcl-slidervalue-compact", "H1"))
      )),
      withTags(div(class = "hcl-sliderwrapper", id = "H2-wrapper",
         withTags(span(class = "hcl-slider",
            sliderInput("H2", label = NULL, -360, 360, default$H2, step = NULL,
                        round = TRUE, ticks = ticks, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class = "hcl-slidervalue",
            textInput("H2val", label = "HUE 2", width  =  NULL),
            actionButton("H2set", "SET")
         )),
         withTags(div(class = "hcl-slidervalue-compact", "H2"))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      )),

      # -------------------------------------------------------------
      # - Show CHOMA
      # -------------------------------------------------------------
<<<<<<< HEAD
      withTags(div(class="hcl-sliderwrapper", id="C1-wrapper",
         withTags(span(class="hcl-slider",
            sliderInput('C1', label=NULL, 0, 100, default$C1, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class="hcl-slidervalue",
            textInput('C1val', label="CHROMA 1", width = NULL),
            actionButton("C1set","SET")
         ))
      )),
      withTags(div(class="hcl-sliderwrapper", id="C2-wrapper",
         withTags(span(class="hcl-slider",
            sliderInput('C2', label=NULL, 0, 100, default$C2, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class="hcl-slidervalue",
            textInput('C2val', label="CHROMA 2", width = NULL),
            actionButton("C2set","SET")
         ))
=======
      withTags(div(class = "hcl-sliderwrapper", id = "C1-wrapper",
         withTags(span(class = "hcl-slider",
            sliderInput("C1", label=NULL, 0, 100, default$C1, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class = "hcl-slidervalue",
            textInput("C1val", label="CHROMA 1", width = NULL),
            actionButton("C1set","SET")
         )),
         withTags(div(class = "hcl-slidervalue-compact", "C1"))
      )),
      withTags(div(class = "hcl-sliderwrapper", id = "CMAX-wrapper",
         withTags(span(class = "hcl-slider",
            sliderInput("CMAX", label=NULL, 0, 100, default$CMAX, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class = "hcl-slidervalue",
            textInput("CMAXval", label="MAX CHROMA", width = NULL),
            actionButton("CMAXset","SET")
         )),
         withTags(div(class = "hcl-slidervalue-compact", "CX"))
      )),
      withTags(div(class = "hcl-sliderwrapper", id = "C2-wrapper",
         withTags(span(class = "hcl-slider",
            sliderInput("C2", label=NULL, 0, 100, default$C2, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class = "hcl-slidervalue",
            textInput("C2val", label="CHROMA 2", width = NULL),
            actionButton("C2set","SET")
         )),
         withTags(div(class = "hcl-slidervalue-compact", "C2"))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      )),

      # -------------------------------------------------------------
      # - Show LUMINANCE
      # -------------------------------------------------------------
<<<<<<< HEAD
      withTags(div(class="hcl-sliderwrapper", id="L1-wrapper",
         withTags(span(class="hcl-slider",
            sliderInput('L1', label=NULL, 0, 100, default$L1, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class="hcl-slidervalue",
            textInput('L1val', label="LUMIN. 1", width = NULL),
            actionButton("L1set","SET")
         ))
      )),
      withTags(div(class="hcl-sliderwrapper", id="L2-wrapper",
         withTags(span(class="hcl-slider",
            sliderInput('L2', label=NULL, 0, 100, default$L2, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width =  NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class="hcl-slidervalue",
            textInput('L2val', label="LUMIN. 2", width = NULL),
            actionButton("L2set","SET")
         ))
=======
      withTags(div(class = "hcl-sliderwrapper", id = "L1-wrapper",
         withTags(span(class = "hcl-slider",
            sliderInput("L1", label = NULL, 0, 100, default$L1, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class = "hcl-slidervalue",
            textInput("L1val", label = "LUMIN. 1", width = NULL),
            actionButton("L1set","SET")
         )),
         withTags(div(class = "hcl-slidervalue-compact", "L1"))
      )),
      withTags(div(class = "hcl-sliderwrapper", id = "L2-wrapper",
         withTags(span(class = "hcl-slider",
            sliderInput("L2", label = NULL, 0, 100, default$L2, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width =  NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class = "hcl-slidervalue",
            textInput("L2val", label = "LUMIN. 2", width = NULL),
            actionButton("L2set","SET")
         )),
         withTags(div(class = "hcl-slidervalue-compact", "L2"))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      )),
   
      # -------------------------------------------------------------
      # - Show POWER
      # -------------------------------------------------------------
<<<<<<< HEAD
      withTags(div(class="hcl-sliderwrapper", id="P1-wrapper",
         withTags(span(class="hcl-slider",
            sliderInput('P1', label=NULL, 0, 3, default$P1, step = 0.05,
                        round = FALSE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class="hcl-slidervalue",
            textInput('P1val', label="POWER 1", width = NULL),
            actionButton("P1set","SET")
         ))
      )),
      withTags(div(class="hcl-sliderwrapper", id="P2-wrapper",
         withTags(span(class="hcl-slider",
            sliderInput('P2', label=NULL, 0, 3, default$P2, step = 0.05,
                        round = FALSE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class="hcl-slidervalue",
            textInput('P2val', label="POWER 2", width = NULL),
            actionButton("P2set","SET")
         ))
=======
      withTags(div(class = "hcl-sliderwrapper", id = "P1-wrapper",
         withTags(span(class = "hcl-slider",
            sliderInput("P1", label = NULL, 0, 3, default$P1, step = 0.05,
                        round = FALSE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class = "hcl-slidervalue",
            textInput("P1val", label = "POWER 1", width = NULL),
            actionButton("P1set","SET")
         )),
         withTags(div(class = "hcl-slidervalue-compact", "P1"))
      )),
      withTags(div(class = "hcl-sliderwrapper", id = "P2-wrapper",
         withTags(span(class = "hcl-slider",
            sliderInput("P2", label = NULL, 0, 3, default$P2, step = 0.05,
                        round = FALSE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class = "hcl-slidervalue",
            textInput("P2val", label="POWER 2", width = NULL),
            actionButton("P2set","SET")
         )),
         withTags(div(class = "hcl-slidervalue-compact", "P1"))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      )),

      # -------------------------------------------------------------
      # - Show NUMBER OF COLORS
      # -------------------------------------------------------------
<<<<<<< HEAD
      withTags(div(class="hcl-sliderwrapper", id="N-wrapper",
         withTags(span(class="hcl-slider",
            sliderInput('N', label=NULL, 2, 40, default$N, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class="hcl-slidervalue",
            textInput('Nval', label="NUMBER", width = NULL),
            actionButton("Nset","SET")
         ))
      )),
      #withTags(div(class="hcl-sliderwrapper", id="LEV-wrapper",
      #   withTags(span(class="hcl-slider",
      #      sliderInput('LEV', label=NULL, 2, 5, default$LEV, step = NULL,
      #                  round = TRUE, ticks = FALSE, animate = FALSE,
      #                  width = NULL, sep = ",", pre = NULL, post = NULL)
      #   )),
      #   withTags(span(class="hcl-slidervalue",
      #      textInput('LEVval', label="LEVELS", width = NULL),
      #      actionButton("LEVset","SET")
      #   ))
      #)),
=======
      withTags(div(class = "hcl-sliderwrapper", id = "N-wrapper",
         withTags(span(class = "hcl-slider",
            sliderInput("N", label=NULL, 2, 40, default$N, step = NULL,
                        round = TRUE, ticks = FALSE, animate = FALSE,
                        width = NULL, sep = ",", pre = NULL, post = NULL)
         )),
         withTags(span(class = "hcl-slidervalue",
            textInput("Nval", label="NUMBER", width = NULL),
            actionButton("Nset", "SET")
         )),
         withTags(div(class = "hcl-slidervalue-compact", "N"))
      )),
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

     
      # -------------------------------------------------------------
      # Color map plot
      # -------------------------------------------------------------
      # The color map
<<<<<<< HEAD
      plotOutput("colormap", height="40px", width="100%"),

      actionButton("closeapp","Return to R"),
=======
      plotOutput("colormap", height = "40px", width = "100%"),

      actionButton("closeapp", "Return to R"),
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

      width = 3
   ),
   mainPanel(

      # -------------------------------------------------------------
      # Main panel which shows the plot.
      # -------------------------------------------------------------
<<<<<<< HEAD
      tabsetPanel(id="maintabs",
         tabPanel("Example Plot",value="plotexample",
            withTags(div(class="hcl-main",id="hcl-main-plot",
=======
      tabsetPanel(id = "maintabs",
         tabPanel("Example Plot", value = "plotexample",
            withTags(div(class = "hcl-main", id = "hcl-main-plot",
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
               plotOutput("plot")
            ))
         ),
      # -------------------------------------------------------------
<<<<<<< HEAD
      # Export panel
      # -------------------------------------------------------------
         tabPanel("Export",value="export",
            withTags(div(class="hcl-main",id="hcl-main-export",
               tabsetPanel(
                  # RAW output (rgb,RGB,hex)
                  tabPanel("RAW",
                     withTags(div(class="output-raw",
                        htmlOutput("exportRAW1"),
                        downloadButton("downloadRAW1","Download")
                     )),
                     withTags(div(class="output-raw",
                        htmlOutput("exportRAW2"),
                        downloadButton("downloadRAW2","Download")
                     )),
                     withTags(div(class="output-raw",
                        htmlOutput("exportRAW3"),
                        downloadButton("downloadRAW3","Download")
                     )),
                     withTags(div(class="output-raw",
=======
      # Show color spectrum
      # -------------------------------------------------------------
         tabPanel("Spectrum", value = "spectrum",
            withTags(div(class = "hcl-main", id = "hcl-main-spectrum",
               plotOutput("spectrum")
            ))
         ),
      # -------------------------------------------------------------
      # Color pane plot
      # -------------------------------------------------------------
         tabPanel("Color Plane", value = "colorplane",
            includeHTML("html/colorplane.html"),
            withTags(div(class = "hcl-main", id = "hcl-main-colorplane",
               plotOutput("colorplane")
            ))
         ),
      # -------------------------------------------------------------
      # Export panel
      # -------------------------------------------------------------
         tabPanel("Export", value = "export", icon = icon("download", lib = "font-awesome"),
            withTags(div(class = "hcl-main", id = "hcl-main-export",
               tabsetPanel(
                  # RAW output (rgb,RGB,hex)
                  tabPanel("RAW",
                     withTags(div(class = "output-raw",
                        htmlOutput("exportRAW1"),
                        downloadButton("downloadRAW1", "Download")
                     )),
                     withTags(div(class = "output-raw",
                        htmlOutput("exportRAW2"),
                        downloadButton("downloadRAW2", "Download")
                     )),
                     withTags(div(class = "output-raw",
                        htmlOutput("exportRAW3"),
                        downloadButton("downloadRAW3", "Download")
                     )),
                     withTags(div(class = "output-raw",
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                        htmlOutput("exportRAW4")
                     ))
                  ),
                  # GrADS style output
                  tabPanel("GrADS",
                     htmlOutput("exportGrADS"),
                     includeHTML("html/GrADS.html")
                  ),
                  # Python output
                  tabPanel("Python",
<<<<<<< HEAD
                     htmlOutput("exportPython"),
                     includeHTML("html/python.html")
=======
                     includeHTML("html/python.html"),
                     htmlOutput("exportPython"),
                     includeHTML("html/python-example.html")
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                  ),
                  # Matlab output
                  tabPanel("matlab",
                     htmlOutput("exportMatlab"),
                     includeHTML("html/matlab.html")
<<<<<<< HEAD
                  )
=======
                  ),
                  # Matlab output
                  tabPanel("R",
                     includeHTML("html/R.html"),
                     htmlOutput("exportFun"),
                     includeHTML("html/RReg.html"),
                     htmlOutput("exportFunReg2")
                  ),
                  # Register palette
                  if ( ! Sys.info()["nodename"] == "sculptor.uberspace.de" ) {
                     tabPanel("Register",
                        includeHTML("html/Register.html"),
                        withTags(span(class = "registerpalette",
                            textInput("registerpalettename", labe = NA, width = "200px"),
                            actionButton("registerpalettebutton","Register")
                        )),
                        includeHTML("html/RegisterRcode.html"),
                        htmlOutput("exportFunReg")
                     )
                  }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
               )
            ))
         ),
      # -------------------------------------------------------------
<<<<<<< HEAD
      # Show color spectrum
      # -------------------------------------------------------------
         tabPanel("Spectrum",value="spectrum",
            withTags(div(class="hcl-main",id="hcl-main-spectrum",
               plotOutput("spectrum")
            ))
         ),
      # -------------------------------------------------------------
      # Main panel which shows the help pages. Hidden in the beginning,
      # displayed on request.
      # -------------------------------------------------------------
         tabPanel("Help Page",value="help",
            withTags(div(class="hcl-main",id="hcl-main-help",
               includeHTML("html/help.html")
=======
      # Main panel which shows the help pages. Hidden in the beginning,
      # displayed on request.
      # -------------------------------------------------------------
         tabPanel("Info", value = "info", icon = icon("info-circle", lib = "font-awesome"),
            withTags(div(class = "hcl-main", id = "hcl-main-help",
               includeHTML("html/info.html")
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            ))
         )
      ),
   

      width = 7

   )
))
