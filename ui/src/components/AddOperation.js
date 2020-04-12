import React, {Component} from "react";
import ChooseAccount from "./ChooseAccount";
import ChooseDate from "./ChooseDate";
import ChooseAmount from "./ChooseAmount";
import ChooseItem from "./ChooseItem";
import Paper from "@material-ui/core/Paper";
import Button from "@material-ui/core/Button";
import withStyles from "@material-ui/core/styles/withStyles";
import SaveIcon from "@material-ui/icons/Save";
import Axios from "axios";

const styles = theme => ({
  root: {
    margin: theme.spacing(1),
    padding: theme.spacing(1),
    display: "flex",
    flexDirection: "column",
  },
  saveButton: {
    marginTop: theme.spacing(2),
  },
});

class AddOperation extends Component {
  state = {
    accountId: "",
    operationDate: new Date().setHours(0, 0, 0, 0),
    amount: "",
    itemId: "",
  };

  handleAccountSelect = ev => {
    this.setState({accountId: ev.target.value});
  };

  handleDateSelect = operationDate => {
    this.setState({operationDate});
  };

  handleAmountSelect = ev => {
    this.setState({amount: ev.target.value});
  };

  handleItemSelect = ev => {
    this.setState({itemId: ev.target.value});
  };

  handleAdd = ev => {
    const {accountId, operationDate, amount, itemId} = this.state;

    Axios.post("http://localhost:8080/api/operations", {
      date: new Date(operationDate).toLocaleDateString(),
      type: "LOSS",
      item: {
        id: itemId,
      },
      account: {
        id: accountId,
      },
      amount,
    })
      .then(() => {
        this.setState({amount: 0});
        alert("добавлено");
      })
      .catch(err => {
        alert(err.response.data.message);
      });
  };

  render() {
    const {accountId, operationDate, amount, itemId} = this.state;
    const {classes} = this.props;

    return (
      <Paper className={classes.root}>
        <ChooseAccount value={accountId} onChange={this.handleAccountSelect} />
        <ChooseDate value={operationDate} onChange={this.handleDateSelect} />
        <ChooseAmount value={amount} onChange={this.handleAmountSelect} />
        <ChooseItem value={itemId} onChange={this.handleItemSelect} />
        <Button
          type="button"
          startIcon={<SaveIcon />}
          className={classes.saveButton}
          onClick={this.handleAdd}
        >
          Добавить
        </Button>
      </Paper>
    );
  }
}

export default withStyles(styles)(AddOperation);
