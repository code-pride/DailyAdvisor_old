import React from 'react';
import { Form, Field, withFormik } from 'formik';
import * as yup from 'yup';

import Button from '../../../../components/Button';

const InnerForm = ({ touched, errors }) => (
    <Form>
        <div>
            <Field type="text" name="username" />
            {touched.username && errors.username && errors.username}
        </div>

        <div>
            <Field type="password" name="password" />
            {touched.password && errors.password && errors.password}
        </div>
        <Button type="submit" value="Zaloguj" />
    </Form>
);

const schema = yup.object().shape({
    username: yup
        .string()
        .email()
        .required(),
    password: yup.string().required(),
});

export const LoginForm = withFormik({
    mapPropsToValues: () => ({
        username: '',
        password: '',
    }),
    validationSchema: schema,
    handleSubmit: (values, { props }) => {
        props.onSubmit(values);
    },
})(InnerForm);
