import React, {Component} from "react";
import {KeyboardDatePicker} from "@material-ui/pickers";

class ChooseDate extends Component {
  render() {
    const {value, onChange} = this.props;

    return (
      <KeyboardDatePicker
        disableToolbar
        variant="inline"
        format="yyyy.MM.dd"
        margin="normal"
        value={value}
        onChange={onChange}
      />
    );
  }
}

export default ChooseDate;
