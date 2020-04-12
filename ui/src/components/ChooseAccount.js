import React, {Component} from "react";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import {InputLabel} from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import axios from "axios";

class ChooseAccount extends Component {
  state = {
    accounts: [],
  };

  componentDidMount() {
    const {value, onChange} = this.props;

    axios
      .get("http://localhost:8080/api/accounts", {crossDomain: true})
      .then(resp => {
        this.setState({accounts: resp.data});
        // в случае если не было передано дефолтного значения и в ответе сервера не пусто,
        // то выбрать первый итем из списка и сделать его текущим
        if (!value && resp.data && resp.data.length) {
          // обернули наш id аккаунта в фейковый ивент
          onChange({target: {value: resp.data[0].id}});
        }
      })
      .catch(console.error);
  }

  render() {
    const {accounts} = this.state;
    const {value, onChange} = this.props;

    return (
      <FormControl>
        <InputLabel>Счет</InputLabel>
        <Select value={value} onChange={onChange}>
          {accounts.map(account => (
            <MenuItem key={account.id} value={account.id}>
              {account.name}, {account.currency.shortName}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    );
  }
}

export default ChooseAccount;
