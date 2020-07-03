import React from "react";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {CssBaseline} from "@material-ui/core";
import {MuiPickersUtilsProvider} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import Paper from "@material-ui/core/Paper";
import {makeStyles} from "@material-ui/core/styles";

import Loss from "./Loss";
import NavBar from "./NavBar";

const useStyles = makeStyles(theme => ({
  app: {
    textAlign: "center",
  },
  content: {
    margin: theme.spacing(1),
    padding: theme.spacing(1),
    display: "flex",
  },
}));

function App() {
  const classes = useStyles();

  return (
    <div className={classes.app}>
      <CssBaseline />
      <Router>
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <NavBar />
          <Paper className={classes.content}>
            <Switch>
              <Route path="/" exact component={Loss} />
              <Route path="/loss" exact component={Loss} />
              <Route path="/courses" exact component={() => "1"} />
            </Switch>
          </Paper>
        </MuiPickersUtilsProvider>
      </Router>
    </div>
  );
}

export default App;
