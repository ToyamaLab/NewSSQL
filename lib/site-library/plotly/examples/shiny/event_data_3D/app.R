<<<<<<< HEAD
library(shiny)
library(plotly)
=======
library(plotly)
library(shiny)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

ui <- fluidPage(
  plotlyOutput("plot"),
  verbatimTextOutput("hover"),
<<<<<<< HEAD
  verbatimTextOutput("click")
=======
  verbatimTextOutput("click"),
  verbatimTextOutput("legendclick"),
  verbatimTextOutput("legend2click"),
  verbatimTextOutput("relayout")
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
)

server <- function(input, output, session) {
  
  output$plot <- renderPlotly({
<<<<<<< HEAD
    plot_ly(x = rnorm(10), y = rnorm(10), z = rnorm(10), type = "scatter3d")
=======
    plot_ly(mtcars, x = ~wt, y = ~mpg, z = ~disp, color = ~factor(cyl)) %>%
      event_register("plotly_legendclick") %>%
      event_register("plotly_legenddoubleclick") 
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  })
  
  output$hover <- renderPrint({
    d <- event_data("plotly_hover")
<<<<<<< HEAD
    if (is.null(d)) "Hover events appear here (unhover to clear)" else d
=======
    if (is.null(d)) "Hover events appear here" else d
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  })
  
  output$click <- renderPrint({
    d <- event_data("plotly_click")
<<<<<<< HEAD
    if (is.null(d)) "Click events appear here (double-click to clear)" else d
=======
    if (is.null(d)) "Click events appear here" else d
  })
  
  output$legendclick <- renderPrint({
    d <- event_data("plotly_legendclick")$name
    if (is.null(d)) "Legend click" else d
  })
  
  output$legend2click <- renderPrint({
    d <- event_data("plotly_legenddoubleclick")$name
    if (is.null(d)) "Legend double-click" else d
  })
  
  output$relayout <- renderPrint({
    d <- event_data("plotly_relayout")$scene.camera$eye
    if (is.null(d)) "Camera eye info" else d
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  })
  
}

<<<<<<< HEAD
shinyApp(ui, server, options = list(display.mode = "showcase"))
=======
shinyApp(ui, server)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
