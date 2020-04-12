import React from "react";
import {NavLink} from "react-router-dom";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import MenuIcon from "@material-ui/icons/Menu";
import LossIcon from "@material-ui/icons/IndeterminateCheckBoxOutlined";
import IncomeIcon from "@material-ui/icons/AddBoxOutlined";
import TransferIcon from "@material-ui/icons/RepeatOutlined";
import AccountsIcon from "@material-ui/icons/AssignmentOutlined";
import IconButton from "@material-ui/core/IconButton";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles(theme => ({
  link: {
    color: "white",
    paddingRight: theme.spacing(2),
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    "&>svg": {
      marginRight: theme.spacing(1),
    },
  },
  activeLink: {
    color: "#3fd5d0",
  },
}));

function NavBar() {
  const classes = useStyles();

  return (
    <AppBar color="primary" position="static">
      <Toolbar>
        <IconButton
          edge="start"
          className={classes.menuButton}
          color="inherit"
          aria-label="menu"
        >
          <MenuIcon />
        </IconButton>
        <NavLink to="/loss" className={classes.link} activeClassName={classes.activeLink}>
          <LossIcon />
          Расходы
        </NavLink>
        <NavLink
          to="/income"
          className={classes.link}
          activeClassName={classes.activeLink}
        >
          <IncomeIcon />
          Доходы
        </NavLink>
        <NavLink
          to="/transfers"
          className={classes.link}
          activeClassName={classes.activeLink}
        >
          <TransferIcon />
          Переводы
        </NavLink>
        <NavLink
          to="/accounts"
          className={classes.link}
          activeClassName={classes.activeLink}
        >
          <AccountsIcon />
          Счета
        </NavLink>
      </Toolbar>
    </AppBar>
  );
}

export default NavBar;
