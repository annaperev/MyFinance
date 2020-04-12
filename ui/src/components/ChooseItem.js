import React, {Component} from "react";
import FormControl from "@material-ui/core/FormControl";
import {InputLabel} from "@material-ui/core";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import axios from "axios";

class ChooseItem extends Component {
  state = {
    items: [],
  };

  componentDidMount() {
    const {value, onChange} = this.props;

    axios
      .get("http://localhost:8080/api/items", {crossDomain: true})
      .then(resp => {
        this.setState({items: resp.data});
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
    const {items} = this.state;
    const {value, onChange} = this.props;
    return (
      <FormControl>
        <InputLabel>Категория</InputLabel>
        <Select value={value} onChange={onChange}>
          {items.map(item => (
            <MenuItem key={item.id} value={item.id}>
              {item.name}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    );
  }
}

export default ChooseItem;
