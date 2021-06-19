library(shiny)
library(plotly)

ui <- fluidPage(
<<<<<<< HEAD
  plotlyOutput("plot"),
  verbatimTextOutput("data")
)

mtcars$id <- row.names(mtcars)

server <- function(input, output, session) {
  
  output$plot <- renderPlotly({
    plot_ly(mtcars, x = ~disp, y = ~mpg) %>%
      add_markers(key = ~id) %>%
      layout(dragmode = "select") %>%
      highlight("plotly_selected")
  })
  
  selected <- reactiveVal(rep(FALSE, nrow(mtcars)))
  
  selected_data <- reactive({
    ed <- event_data("plotly_selected")
    if (is.null(ed)) return(NULL)
    new <- mtcars[["id"]] %in% ed[["key"]]
    selected(selected() | new)
    mtcars[selected(), ]
  })
  
  output$data <- renderPrint({
    selected_data()
=======
  plotlyOutput("p"),
  tableOutput("table")
)

server <- function(input, output, session) {
  
  # keep track of which cars have been hovered on
  cars <- reactiveVal()
  
  # On hover, the key field of the event data contains the car name
  # Add that name to the set of all "selected" cars
  observeEvent(event_data("plotly_hover"), {
    car <- event_data("plotly_hover")$customdata
    cars_old_new <- c(cars(), car)
    cars(unique(cars_old_new))
  })
  
  # clear the set of cars when a double-click occurs
  observeEvent(event_data("plotly_doubleclick"), {
    cars(NULL)
  })
  
  output$p <- renderPlotly({
    
    # if the car is selected, paint it red
    cols <- ifelse(row.names(mtcars) %in% cars(), "red", "black")
    
    mtcars %>%
      plot_ly(
        x = ~wt, y = ~mpg, 
        customdata = row.names(mtcars), 
        marker = list(color = cols)
      ) %>%
      add_markers()
  })
  
  output$table <- renderTable({
    filter(mtcars, row.names(mtcars) %in% cars())
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  })
  
}

shinyApp(ui, server)
