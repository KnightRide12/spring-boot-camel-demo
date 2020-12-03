#/bin/bash
rm $HOME/.kube/config
sudo cp -R /home/$CLOUDUSER/cluster-$GUID $HOME
sudo chown -R $(whoami):users $HOME/cluster-$GUID
API_URL="$(oc whoami --kubeconfig $HOME/cluster-$GUID/auth/kubeconfig --show-server)"
KUBEADMIN_PASSWORD="$(cat $HOME/cluster-$GUID/auth/kubeadmin-password)"
echo "export API_URL=$API_URL" >> ~/.bashrc
echo "export KUBEADMIN_PASSWORD=$KUBEADMIN_PASSWORD" >> ~/.bashrc
