import React, {Component} from "react";
import Input from "@material-ui/core/Input";
import FormControl from "@material-ui/core/FormControl";
import {InputLabel} from "@material-ui/core";

class ChooseAmount extends Component {
  render() {
    const {value, onChange} = this.props;

    return (
      <FormControl>
        <InputLabel>Сумма</InputLabel>
        <Input
          defaultValue="Введите сумму"
          type="number"
          value={value}
          onChange={onChange}
        />
      </FormControl>
    );
  }
}

export default ChooseAmount;
